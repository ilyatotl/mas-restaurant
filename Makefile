build:
	./gradlew :jar
	cp build/libs/mas-restaurant-1.0-SNAPSHOT.jar restaurant.jar

run:
	java -jar restaurant.jar

update:
	make -B build
	make run