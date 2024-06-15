.PHONY: jar
jar:
	./gradlew runDatagenClient
	./gradlew assemble
	cp build/libs/mmp-*.jar run/mods/
