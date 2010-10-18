#! /bin/sh
rsync -avzC --no-p -O -e ssh "jMIPS Update Site/" mars:/www/PROG/software/jmips
