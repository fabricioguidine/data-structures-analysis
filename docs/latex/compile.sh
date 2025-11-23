#!/bin/bash
# Bash script to compile LaTeX report to PDF
# Usage: ./compile.sh

echo "LaTeX Report Compilation Script"
echo "================================"
echo ""

# Check if pdflatex is available
if ! command -v pdflatex &> /dev/null; then
    echo "ERROR: pdflatex is not installed or not in PATH."
    echo ""
    echo "To compile the PDF, please:"
    echo "1. Install TeX Live: https://www.tug.org/texlive/"
    echo "   OR MiKTeX: https://miktex.org/download"
    echo ""
    echo "2. Alternatively, use Overleaf (online): https://www.overleaf.com/"
    echo "   - Create account"
    echo "   - Create new project"
    echo "   - Upload relatorio.tex and referencias.bib"
    echo "   - Compile and download PDF"
    echo ""
    echo "3. After compilation, save the PDF as: relatorio.pdf"
    echo ""
    exit 1
fi

echo "Found pdflatex: $(which pdflatex)"
echo ""

# Check if bibtex is available
if ! command -v bibtex &> /dev/null; then
    echo "WARNING: bibtex is not found. Bibliography may not be processed correctly."
fi

echo "Compiling LaTeX report..."
echo ""

# Change to script directory
cd "$(dirname "$0")"

# First pass
echo "Running pdflatex (pass 1/3)..."
pdflatex -interaction=nonstopmode relatorio.tex > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "Error in first pdflatex pass. Check relatorio.log for details."
    exit 1
fi

# Bibliography pass
if command -v bibtex &> /dev/null; then
    echo "Running bibtex..."
    bibtex relatorio > /dev/null 2>&1
fi

# Second pass
echo "Running pdflatex (pass 2/3)..."
pdflatex -interaction=nonstopmode relatorio.tex > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "Error in second pdflatex pass. Check relatorio.log for details."
    exit 1
fi

# Third pass
echo "Running pdflatex (pass 3/3)..."
pdflatex -interaction=nonstopmode relatorio.tex > /dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "Error in third pdflatex pass. Check relatorio.log for details."
    exit 1
fi

echo ""
echo "SUCCESS! PDF compiled successfully."
echo "PDF file: relatorio.pdf"
echo ""

# Check if PDF exists
if [ -f "relatorio.pdf" ]; then
    PDF_SIZE=$(du -h relatorio.pdf | cut -f1)
    echo "PDF size: $PDF_SIZE"
    echo ""
    echo "To commit the PDF to the repository:"
    echo "  git add docs/latex/relatorio.pdf"
    echo "  git commit -m 'Add compiled LaTeX report PDF'"
else
    echo "WARNING: PDF file not found after compilation."
fi

echo ""
echo "Cleaning up auxiliary files..."
rm -f *.aux *.log *.out *.toc *.lof *.lot *.bbl *.blg *.idx *.ilg *.ind *.synctex.gz
echo "Done!"

