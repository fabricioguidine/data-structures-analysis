# Technical Report - LaTeX

This directory contains the LaTeX source files for the technical report analyzing the experimental results from the Data Structures and Algorithms Performance Analysis project.

## Structure

- `relatorio.tex` - Main LaTeX document (uses abnTeX2)
- `referencias.bib` - Bibliographic references
- `Makefile` - Build automation

## Requirements

To compile this report, you need:

- LaTeX distribution (TeX Live or MiKTeX)
- abnTeX2 package
- Additional packages (see `relatorio.tex` for complete list)

## Compilation

### Using Makefile (recommended):

```bash
cd report
make pdf
```

### Manual compilation:

```bash
cd report
pdflatex relatorio.tex
bibtex relatorio
pdflatex relatorio.tex
pdflatex relatorio.tex
```

## Results Analysis

The report analyzes results from the `../tests/` directory structure:

### Sorting Algorithms
- Results from: `../tests/sorting/quicksort/` and `../tests/sorting/heapsort/`
- Performance metrics: comparisons, swaps, execution time

### Hash Tables
- Results from: `../tests/hashtable/`
- Most frequent authors analysis
- Performance metrics: insertion time, search efficiency

### Tree Structures
- Results from: `../tests/trees/`
  - Red-Black Tree: `../tests/trees/redblack/`
  - B+ Tree (d=2): `../tests/trees/bplustree/d2/`
  - B+ Tree (d=20): `../tests/trees/bplustree/d20/`
- Performance metrics: insertion and search operations

### Comparison Analysis
- Comparative results: `../tests/trees/comparison/`
- Overall results summary: `../tests/results/`

## Adding Results

1. Run experiments and save results to `../tests/` directories
2. Create analysis files in LaTeX format (`.tex`) or text format
3. Update `relatorio.tex` to include results using `\input{}` commands
4. Recompile the report

## Notes

- The report follows ABNT standards using abnTeX2
- Results should be added as tables or figures
- Update the bibliography (`referencias.bib`) as needed
- Run compilation multiple times to resolve all references
