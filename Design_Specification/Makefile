# Adapted from doxygen generated Makefile

LATEX_CMD=pdflatex

all: spec.pdf

pdf: spec.pdf

spec.pdf: clean spec.tex
	$(LATEX_CMD) spec
	latex_count=8 ; \
	while egrep -s 'Rerun (LaTeX|to get cross-references right)' spec.log && [ $$latex_count -gt 0 ] ;\
	    do \
	      echo "Rerunning latex...." ;\
	      $(LATEX_CMD) spec ;\
	      latex_count=`expr $$latex_count - 1` ;\
	    done
	$(LATEX_CMD) spec


clean:
	rm -f *.ps *.dvi *.aux *.toc *.idx *.ind *.ilg *.log *.out *.brf *.blg *.bbl spec.pdf
