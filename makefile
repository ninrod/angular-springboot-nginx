.PHONY: backend up db logsdb down build

up:
	docker-compose up -d

db:
	docker-compose up -d db

backend:
	docker-compose up -d backend

frontend:
	docker-compose up -d frontend

logsdb:
	docker logs -f db

down:
	docker-compose down && docker-compose rm

build:
	gradle build -x test


