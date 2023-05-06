.PHONY: jar
jar:
	export JAVA_HOME=`/usr/libexec/java_home -v17`
	eval "`python3 ./.scripts/get_version.py`"
	./gradlew runDatagenClient
	./gradlew assemble
	cp build/libs/mmp-$(MMP_VERSION).jar ~/Library/Application\ Support/minecraft/mods/
