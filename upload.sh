#! /bin/sh
rsync -avzC --no-p -O -e ssh "javaMIPS Update Site/" mars:/www/PROG/software/javamips
