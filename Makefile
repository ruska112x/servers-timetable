all: build run

clean:
	mvn clean

build: clean
	mvn package

run:
	java -jar target/timetable.jar

clean-pgdata:
	docker volume rm timetable_pgdata
