.PHONY: run stop test docker-build docker-run

run:
	./gradlew bootRun

stop:
	pkill -f 'kcb-demo' || true

test:
	./gradlew test

docker-build:
	docker build -t kcb-demo:local .

docker-run:
	docker run --rm -p 8080:8080 \
	-e DB_HOST=host.docker.internal -e DB_PORT=5432 -e DB_NAME=app -e DB_USER=postgres -e DB_PASSWORD=postgres \
	kcb-demo:local
