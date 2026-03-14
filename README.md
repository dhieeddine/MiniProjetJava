# 🔍 MiniProjetJava — Name Matching & Deduplication Engine

A pure Java console application implementing a flexible, modular **name matching and record linkage engine**. It supports fuzzy search, cross-list comparison, and deduplication of name datasets (e.g. CSV files with hundreds of thousands of names).

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [Components](#components)
  - [Preprocessors (Prétraiteurs)](#preprocessors-prétraiteurs)
  - [Candidate Generators (Générateurs)](#candidate-generators-générateurs)
  - [Comparators (Comparateurs)](#comparators-comparateurs)
  - [Selectors (Sélectionneurs)](#selectors-sélectionneurs)
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [CSV Format](#csv-format)

---

## Overview

MiniProjetJava is a **record linkage / entity resolution engine** focused on name matching. Given one or two lists of names (loaded from CSV files), it can:

- **Search** for names similar to a query in a large list
- **Compare two lists** to find matching name pairs across them
- **Deduplicate** a single list by detecting near-identical entries

The engine is fully configurable at runtime via an interactive menu: you choose the preprocessing pipeline, the similarity algorithm, the candidate generation strategy, and the result selection policy.

---

## Tech Stack

| Technology | Details |
|---|---|
| Language | Java (module-based, Java 9+) |
| Build | Eclipse / Gradle (Buildship) |
| I/O | Console (Scanner) + CSV file reader |
| Dependencies | None — pure Java standard library |

---

## Project Structure

```
MiniProjetJava/
├── src/
│   ├── module-info.java
│   └── MiniProjet/
│       ├── Application.java                      # Entry point & interactive menus
│       ├── MoteurMatching.java                   # Core matching engine
│       │
│       ├── EntiteNom.java                        # Name entity (raw + preprocessed)
│       ├── CoupleDeNom.java                      # Pair of names (candidate)
│       ├── CoupleNomsScore.java                  # Pair of names + similarity score
│       │
│       ├── Pretraiteur.java                      # Interface: preprocessor
│       ├── PretraiteurMinuscule.java             # Lowercase
│       ├── PretraiteurSansAccents.java           # Accent removal
│       ├── PretraiteurSansCaracteresSpeciaux.java # Special character removal
│       ├── PretraiteurPhonetique.java            # Phonetic normalization
│       ├── PretraiteurDeTRiNom.java              # Word-order normalization (sort tokens)
│       │
│       ├── GenerateurCandidats.java              # Interface: candidate generator
│       ├── GenerateurDeTousLesCouples.java       # All pairs (brute force)
│       ├── GenerateurAleatoire.java              # Random N pairs
│       ├── GenerateurAvecPrefix.java             # Prefix-indexed pairs
│       ├── GenerateurParTaille.java              # Length-indexed pairs
│       │
│       ├── ComparateurNoms.java                  # Interface: similarity metric
│       ├── ComparateurExact.java                 # Exact match
│       ├── ComparateurLevenstein.java            # Levenshtein edit distance
│       ├── ComparateurJaroWinkler.java           # Jaro-Winkler similarity
│       ├── ComparateurNGram.java                 # N-gram overlap (Dice coefficient)
│       ├── ComparateurScore.java                 # Comparator for sorting by score
│       │
│       ├── SelectionneurDeResultatsDeMatching.java # Interface: result selector
│       ├── SelectionneurDeTousLesResultats.java  # Return all results
│       ├── SelectionneurAvecSeuil.java           # Filter by score threshold
│       ├── SelectionneurNPremiers.java           # Top-N results by score
│       │
│       ├── Indexeur.java                         # Interface: indexer
│       ├── IndexeurHashMap.java                  # HashMap index by name length
│       │
│       ├── Recuperateur.java                     # Interface: data loader
│       └── RecuperateurCSV.java                  # CSV file loader
└── bin/
    └── module-info.class
```

---

## Architecture

The engine follows a clean **Strategy pattern** pipeline:

```
CSV Input
    │
    ▼
[ Récupérateur ]        Load names from CSV
    │
    ▼
[ Prétraiteur(s) ]      Normalize names (lowercase, accents, phonetics...)
    │
    ▼
[ Générateur de candidats ]   Generate candidate pairs to compare
    │
    ▼
[ Comparateur ]         Score each pair with a similarity metric
    │
    ▼
[ Sélectionneur ]       Filter / rank the results
    │
    ▼
Console Output          Display matches with scores + execution time
```

Each step is an **interface** with multiple interchangeable implementations, all configurable at runtime.

---

## Components

### Preprocessors (Prétraiteurs)

Applied sequentially to normalize names before comparison. Multiple can be stacked.

| Class | Effect |
|---|---|
| `PretraiteurMinuscule` | Converts names to lowercase |
| `PretraiteurSansAccents` | Removes diacritics (é → e, ü → u, etc.) |
| `PretraiteurSansCaracteresSpeciaux` | Strips non-letter characters |
| `PretraiteurPhonetique` | Removes vowels, applies phonetic substitutions (ph→f, q→k, x→ks) |
| `PretraiteurDeTRiNom` | Sorts name tokens alphabetically (e.g. "Jean Pierre" → "Jean Pierre" sorted) |

**Default pipeline:** Lowercase → Remove accents → Sort tokens → Phonetic

---

### Candidate Generators (Générateurs)

Control which name pairs are actually compared — critical for performance at scale.

| Class | Strategy | Complexity |
|---|---|---|
| `GenerateurDeTousLesCouples` | All possible pairs (cartesian product) | O(n²) |
| `GenerateurAleatoire(n)` | n random pairs | O(n) |
| `GenerateurAvecPrefix(3)` | Only pairs sharing a 3-character prefix | O(n) avg |
| `GenerateurParTaille` | Only pairs with similar name lengths (±1 char) | O(n) avg |

**Default:** `GenerateurAvecPrefix`

---

### Comparators (Comparateurs)

Compute a similarity score between 0.0 and 1.0 for a pair of preprocessed names.

| Class | Algorithm | Best for |
|---|---|---|
| `ComparateurExact` | Exact string equality | Strict dedup |
| `ComparateurLevenstein` | Normalized edit distance | Typos, OCR errors |
| `ComparateurJaroWinkler` | Jaro-Winkler similarity | Short names, transpositions |
| `ComparateurNGram(n)` | Dice coefficient on n-grams | Substring variations |

**Default:** `ComparateurLevenstein`

---

### Selectors (Sélectionneurs)

Filter or rank the scored results before displaying them.

| Class | Behaviour |
|---|---|
| `SelectionneurDeTousLesResultats` | Returns all scored pairs |
| `SelectionneurAvecSeuil(t)` | Returns only pairs with score ≥ threshold `t` |
| `SelectionneurNPremiers(n)` | Returns the top-n pairs ranked by score (descending) |

**Default:** `SelectionneurAvecSeuil(0.6)`

---

## Features

### 🔎 1. Name Search
Search for a name within a loaded list. The engine preprocesses both the query and the list, generates candidates, scores them, and returns ranked matches.

### 🔗 2. List Comparison
Compare two CSV lists against each other and find cross-list matches above a configurable threshold.

### 🗂️ 3. List Deduplication
Detect near-duplicate entries within a single list. Uses length-based indexing for efficiency and prints pairs with a score ≥ 0.99.

### ⚙️ 4. Full Runtime Configuration
All pipeline components (preprocessors, comparator, candidate generator, selector) can be swapped at runtime via the configuration menu — no recompilation needed.

---

## Getting Started

### Prerequisites

- Java 9+ (uses Java modules)
- Eclipse IDE with Buildship, or any Java IDE

### Clone & Run

```bash
git clone https://github.com/dhieeddine/MiniProjetJava.git
cd MiniProjetJava
```

Open in Eclipse as a Gradle project, then run `Application.java` as a Java Application.

Alternatively, compile and run manually:

```bash
javac -d bin src/module-info.java src/MiniProjet/*.java
java --module-path bin -m <module-name>/MiniProjet.Application
```

---

## Usage

On launch, the main menu is displayed:

```
===== MENU PRINCIPAL =====

1. Effectuer une recherche
2. Comparer deux listes
3. Dédupliquer une liste
4. Configurer les paramètres
5. Quitter
```

Before running a search or comparison, go to **option 4** to configure:
- Which preprocessors to apply
- Which similarity metric to use
- Which candidate generator to use
- Which result selector to apply

---

## CSV Format

The CSV loader (`RecuperateurCSV`) expects the following format:

```
id,name
1,Jean Dupont
2,Marie Curie
3,Ahmed Ben Ali
```

- **First column:** unique identifier
- **Second column:** full name
- First row is treated as a header and skipped automatically

> The path to the CSV file is entered interactively at runtime when a search or comparison is triggered.

---

## Notes

- Execution time is printed after every operation for benchmarking.
- The deduplication engine caps output at **40,000 duplicate pairs** for performance.
- The phonetic preprocessor implements a simplified French-oriented scheme (vowel removal + basic substitutions).
