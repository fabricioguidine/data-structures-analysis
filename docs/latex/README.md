# LaTeX Technical Report

This directory contains the LaTeX source files for the technical report of the Data Structures Analysis project.

## Structure

- `relatorio.tex` - Main LaTeX document
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
make pdf
```

### Manual compilation:

```bash
pdflatex relatorio.tex
bibtex relatorio
pdflatex relatorio.tex
pdflatex relatorio.tex
```

## Adding Results

The report includes placeholders for results from:
- `../../tests/sorting/` - Sorting algorithm results
- `../../tests/hashtable/` - Hash table results
- `../../tests/trees/` - Tree structure results

Add your experimental results to these directories and include them in the LaTeX document using `\input{}` commands.

## Notes

- The report follows ABNT standards using abnTeX2
- Results should be added as tables or figures
- Update the bibliography as needed
- Run compilation multiple times to resolve all references

