# LaTeX PDF Compilation Instructions

## Quick Start

To compile the LaTeX report to PDF:

```bash
cd docs/latex
make pdf
```

**Output:** `relatorio.pdf` will be created in `docs/latex/` directory.

## Requirements

1. **LaTeX Distribution:**
   - TeX Live (Linux/Mac): https://www.tug.org/texlive/
   - MiKTeX (Windows): https://miktex.org/
   - Overleaf (Online): https://www.overleaf.com/

2. **Required Packages:**
   - abnTeX2
   - booktabs
   - longtable
   - listings
   - xcolor
   - hyperref
   - amsmath
   - float
   - caption
   - subcaption
   - pgfplots
   - pgfplotstable

## Compilation Steps

### Option 1: Make (Linux/Mac/Git Bash)

```bash
cd docs/latex
make pdf
```

### Option 2: Manual (All Platforms)

```bash
cd docs/latex
pdflatex relatorio.tex
bibtex relatorio
pdflatex relatorio.tex
pdflatex relatorio.tex
```

### Option 3: Overleaf (Online)

1. Create account at https://www.overleaf.com/
2. Create new project
3. Upload:
   - `relatorio.tex`
   - `referencias.bib`
4. Set compiler: pdfLaTeX
5. Compile → Download PDF
6. Save as `relatorio.pdf` in `docs/latex/`

## After Compilation

1. **Verify PDF exists:** `docs/latex/relatorio.pdf`
2. **Commit to repository:**
   ```bash
   git add docs/latex/relatorio.pdf
   git commit -m "Add compiled LaTeX report PDF"
   ```

## Troubleshooting

### Missing abnTeX2
- **TeX Live:** `tlmgr install abntex2`
- **MiKTeX:** Package Manager → Install abnTeX2
- **Overleaf:** Select "XeLaTeX" or "pdfLaTeX" compiler

### Compilation Errors
- Check `relatorio.log` for error details
- Ensure all packages are installed
- Verify file encoding is UTF-8

### Missing References
- Run `bibtex relatorio` before final `pdflatex`
- Ensure `referencias.bib` is in same directory

## Current Status

- ✅ LaTeX source file is complete and in English
- ✅ All tables and structure are in place
- ✅ Author email included: fabricio.guidine@estudante.ufjf.br
- ⏳ PDF compilation required (LaTeX not available on build system)

## Notes

- The PDF file should be committed to the repository for easy access
- Build artifacts (*.aux, *.log, etc.) are ignored by git
- The compiled PDF is the final deliverable for the technical report

