# Variables
IMAGE_NAME = challenge:1.0.0
DEPLOYMENT_NAME = challenge-app

# Build the Docker image
build:
	mvn clean install
	# docker build -t challenge-app:latest . --no-cache
	docker build -t $(DEPLOYMENT_NAME):latest . --no-cache

# Deploy local
deploy-local: build
	docker-compose up

# Clean local
clean-local:
	docker-compose down

# Setup Minikube (assuming exists)
setup-minikube:
	minikube start
	minikube addons enable ingress

# Deploy to Minikube
deploy-minikube: build
	# Minikube's built-in Docker daemon.
	eval $$(minikube docker-env)
	# Use Minikube's daemon
	make build
	# Apply kubernetes manifests
	kubectl apply -f .chart/manifests/

# Test Application
test:
	# Test commands go here, for example:
	mvn test

# Minikube clean up
clean-minikube:
	kubectl delete -f .chart/manifests/
	minikube stop
	minikube delete

.PHONY: build setup-minikube deploy-minikube test clean-minikube
