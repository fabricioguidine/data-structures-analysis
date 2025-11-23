# Technical Report - LaTeX

This directory contains the LaTeX source files for the technical report analyzing the experimental results from the Performance Analysis for Data Structures and Algorithms project.

## Structure

- `relatorio.tex` - Main LaTeX document (uses abnTeX2)
- `referencias.bib` - Bibliographic references
- `Makefile` - Build automation
- `compile.ps1` - Windows PowerShell compilation script
- `compile.sh` - Linux/Mac Bash compilation script
- `relatorio.pdf` - Compiled PDF report (needs to be generated)
- `GENERATE_PDF_NOW.md` - Quick guide to generate PDF (START HERE!)

## Requirements

To compile this report, you need:

- LaTeX distribution (TeX Live or MiKTeX)
- abnTeX2 package
- Additional packages (see `relatorio.tex` for complete list):
  - `booktabs`
  - `longtable`
  - `listings`
  - `xcolor`
  - `hyperref`
  - `amsmath`
  - `float`
  - `caption`
  - `subcaption`
  - `pgfplots`
  - `pgfplotstable`

## Compilation

### Using Makefile (recommended):

```bash
cd docs/latex
make pdf
```

### Manual compilation:

```bash
cd docs/latex
pdflatex relatorio.tex
bibtex relatorio
pdflatex relatorio.tex
pdflatex relatorio.tex
```

## Results Analysis

The report analyzes results from the `../../tests/` directory structure:

### Sorting Algorithms
- Results from: `../../tests/sorting/quicksort/` and `../../tests/sorting/heapsort/`
- Performance metrics: comparisons, swaps, execution time

### Hash Tables
- Results from: `../../tests/hashtable/`
- Most frequent authors analysis
- Performance metrics: insertion time, search efficiency

### Tree Structures
- Results from: `../../tests/trees/`
- Red-Black Tree: `../../tests/trees/redblack/`
- B+ Tree (d=2): `../../tests/trees/bplustree/d2/`
- B+ Tree (d=20): `../../tests/trees/bplustree/d20/`

## Author Information

- **Fabrício Guidine**: fabricio.guidine@estudante.ufjf.br
- Débora Duarte
- Walkíria Garcia

## Notes

- The report uses abnTeX2 template following ABNT standards
- Tables will be automatically populated when results are available in the `tests/` directory
- Placeholder tables are included for structure and will be filled with actual experimental data
- **The report is written in English** as required
- **The compiled PDF file `relatorio.pdf` should be present in this directory**

## Generating the PDF

### Prerequisites

1. Install LaTeX distribution:
   - **TeX Live** (Linux/Mac): https://www.tug.org/texlive/
   - **MiKTeX** (Windows): https://miktex.org/
   - **Overleaf** (Online): https://www.overleaf.com/ (alternative option)

2. Install abnTeX2 package:
   - Usually included in full TeX Live/MiKTeX installations
   - If not: Install via package manager or tlmgr

### Compilation Methods

#### Method 1: Using Make (Recommended)

```bash
cd docs/latex
make pdf
```

#### Method 2: Manual Compilation

```bash
cd docs/latex
pdflatex relatorio.tex
bibtex relatorio
pdflatex relatorio.tex
pdflatex relatorio.tex
```

#### Method 3: Using Overleaf (Online)

1. Go to https://www.overleaf.com/
2. Create a new project
3. Upload all files from `docs/latex/`:
   - `relatorio.tex`
   - `referencias.bib`
4. Set compiler to pdfLaTeX
5. Compile and download the PDF

### Output

The compiled PDF will be saved as `relatorio.pdf` in the `docs/latex/` directory.

**Important:** After compilation, commit the PDF file to the repository:

```bash
git add docs/latex/relatorio.pdf
git commit -m "Add compiled LaTeX report PDF"
```

### Troubleshooting

- **Missing abnTeX2**: Install via `tlmgr install abntex2` (TeX Live) or MiKTeX Package Manager
- **Compilation errors**: Check `.log` file for detailed error messages
- **Missing packages**: LaTeX will prompt for missing packages (auto-install in MiKTeX)
