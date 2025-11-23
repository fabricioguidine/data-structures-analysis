# PowerShell script to compile LaTeX report to PDF
# Usage: .\compile.ps1

Write-Host "LaTeX Report Compilation Script" -ForegroundColor Cyan
Write-Host "================================" -ForegroundColor Cyan
Write-Host ""

# Check if pdflatex is available
$pdflatex = Get-Command pdflatex -ErrorAction SilentlyContinue

if ($null -eq $pdflatex) {
    Write-Host "ERROR: pdflatex is not installed or not in PATH." -ForegroundColor Red
    Write-Host ""
    Write-Host "To compile the PDF, please:" -ForegroundColor Yellow
    Write-Host "1. Install MiKTeX (Windows): https://miktex.org/download" -ForegroundColor Yellow
    Write-Host "   OR TeX Live: https://www.tug.org/texlive/" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "2. Alternatively, use Overleaf (online): https://www.overleaf.com/" -ForegroundColor Yellow
    Write-Host "   - Create account" -ForegroundColor Yellow
    Write-Host "   - Create new project" -ForegroundColor Yellow
    Write-Host "   - Upload relatorio.tex and referencias.bib" -ForegroundColor Yellow
    Write-Host "   - Compile and download PDF" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "3. After compilation, save the PDF as: relatorio.pdf" -ForegroundColor Yellow
    Write-Host ""
    exit 1
}

Write-Host "Found pdflatex: $($pdflatex.Source)" -ForegroundColor Green
Write-Host ""

# Check if bibtex is available
$bibtex = Get-Command bibtex -ErrorAction SilentlyContinue

if ($null -eq $bibtex) {
    Write-Host "WARNING: bibtex is not found. Bibliography may not be processed correctly." -ForegroundColor Yellow
}

Write-Host "Compiling LaTeX report..." -ForegroundColor Cyan
Write-Host ""

# Change to latex directory
Push-Location $PSScriptRoot

try {
    # First pass
    Write-Host "Running pdflatex (pass 1/3)..." -ForegroundColor Yellow
    pdflatex -interaction=nonstopmode relatorio.tex | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Error in first pdflatex pass. Check relatorio.log for details." -ForegroundColor Red
        exit 1
    }

    # Bibliography pass
    if ($null -ne $bibtex) {
        Write-Host "Running bibtex..." -ForegroundColor Yellow
        bibtex relatorio | Out-Null
    }

    # Second pass
    Write-Host "Running pdflatex (pass 2/3)..." -ForegroundColor Yellow
    pdflatex -interaction=nonstopmode relatorio.tex | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Error in second pdflatex pass. Check relatorio.log for details." -ForegroundColor Red
        exit 1
    }

    # Third pass
    Write-Host "Running pdflatex (pass 3/3)..." -ForegroundColor Yellow
    pdflatex -interaction=nonstopmode relatorio.tex | Out-Null
    if ($LASTEXITCODE -ne 0) {
        Write-Host "Error in third pdflatex pass. Check relatorio.log for details." -ForegroundColor Red
        exit 1
    }

    Write-Host ""
    Write-Host "SUCCESS! PDF compiled successfully." -ForegroundColor Green
    Write-Host "PDF file: relatorio.pdf" -ForegroundColor Green
    Write-Host ""

    # Check if PDF exists
    if (Test-Path "relatorio.pdf") {
        $pdfSize = (Get-Item "relatorio.pdf").Length / 1KB
        Write-Host "PDF size: $([math]::Round($pdfSize, 2)) KB" -ForegroundColor Green
        Write-Host ""
        Write-Host "To commit the PDF to the repository:" -ForegroundColor Cyan
        Write-Host "  git add docs/latex/relatorio.pdf" -ForegroundColor White
        Write-Host "  git commit -m 'Add compiled LaTeX report PDF'" -ForegroundColor White
    } else {
        Write-Host "WARNING: PDF file not found after compilation." -ForegroundColor Yellow
    }

} catch {
    Write-Host "ERROR: Compilation failed: $_" -ForegroundColor Red
    exit 1
} finally {
    Pop-Location
}

Write-Host ""
Write-Host "Cleaning up auxiliary files..." -ForegroundColor Cyan
Remove-Item -Path *.aux,*.log,*.out,*.toc,*.lof,*.lot,*.bbl,*.blg,*.idx,*.ilg,*.ind -ErrorAction SilentlyContinue
Write-Host "Done!" -ForegroundColor Green

