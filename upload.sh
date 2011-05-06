#! /bin/sh
rsync -avzC --no-p -O -e ssh "javaMIPS Update Site/" mars:/www/CDL/software/javamips
