up:
	docker-compose up -d

db:
	docker-compose up -d db

logsdb:
	docker logs -f db

down:
	docker-compose down && docker-compose rm

build:
	gradle build -x test


