#!/bin/sh

FILENAME=`ls -1 javaMIPS\ Update\ Site/plugins/de.unisb.prog.mips.core_*.jar`;

if [ -z "$FILENAME" ]; then
    echo Could not find the mips core jar \(javaMIPS Update Site/plugins/de.unisb.prog.mips.core_*.jar\)
    exit
fi

VERSION=${FILENAME##javaMIPS\ Update\ Site/plugins/de.unisb.prog.mips.core_}
VERSION=${VERSION%%.jar}

echo Deploying MIPS Core Version $VERSION

echo mvn deploy:deploy-file -Dfile="${FILENAME}" -DrepositoryId=titania -Durl=http://titania.fs.uni-saarland.de/nexus/content/repositories/prog/ -DgroupId=de.unisb.prog.mips -DartifactId=core -Dpackaging=jar -Dversion=${VERSION}
mvn2 deploy:deploy-file -Dfile="${FILENAME}" -DrepositoryId=titania -Durl=http://titania.fs.uni-saarland.de/nexus/content/repositories/prog/ -DgroupId=de.unisb.prog.mips -DartifactId=core -Dpackaging=jar -Dversion=${VERSION}
