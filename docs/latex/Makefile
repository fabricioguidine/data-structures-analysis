# Makefile for LaTeX Report Compilation
# Usage: make pdf

LATEX = pdflatex
BIBTEX = bibtex
MAIN = relatorio
TEX = $(MAIN).tex
PDF = $(MAIN).pdf

all: pdf

pdf:
	$(LATEX) $(TEX)
	$(BIBTEX) $(MAIN)
	$(LATEX) $(TEX)
	$(LATEX) $(TEX)

clean:
	rm -f *.aux *.log *.out *.toc *.lof *.lot *.bbl *.blg *.idx *.ilg *.ind

clean-all: clean
	rm -f $(PDF)

.PHONY: all pdf clean clean-all

