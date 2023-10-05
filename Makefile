

# Variables
DEPLOYMENT_NAME = challenge-app

# Build the Docker image
build:
	mvn clean install
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
deploy-minikube: build setup-minikube
	minikube image load $(DEPLOYMENT_NAME):latest
	# Apply kubernetes manifests
	kubectl apply -f .chart/manifests/postgresql-secret.yaml
	kubectl apply -f .chart/manifests/postgresql.yaml
	kubectl apply -f .chart/manifests/service.yaml
	kubectl apply -f .chart/manifests/deployment.yaml

	echo "visit http://$$(minikube ip):31000/v3/api-docs"

# Minikube clean up
clean-minikube:
	kubectl delete -f .chart/manifests/
	minikube stop
	minikube delete

# Test Application
test:
	# Test commands go here, for example:
	mvn test


.PHONY: build setup-minikube deploy-minikube test clean-minikube deploy-local clean-local

# GKE Deployment
GKE_NAMESPACE = challenge-ns

deploy-gke: build push-gke
	# Apply kubernetes manifests
	kubectl apply -f .chart/manifests-gke/postgresql-secret.yaml -n $(GKE_NAMESPACE)
	kubectl apply -f .chart/manifests-gke/postgresql.yaml -n $(GKE_NAMESPACE)
	kubectl apply -f .chart/manifests-gke/service.yaml -n $(GKE_NAMESPACE)
	kubectl apply -f .chart/manifests-gke/deployment.yaml -n $(GKE_NAMESPACE)

DOCKER_HUB_PATH = drmendes/$(DEPLOYMENT_NAME):latest

push-gke:
	docker tag $(DEPLOYMENT_NAME):latest $(DOCKER_HUB_PATH)
	docker push $(DOCKER_HUB_PATH)

# GKE Cleanup
clean-gke:
	kubectl delete -f .chart/manifests-gke/postgresql-secret.yaml -n $(GKE_NAMESPACE)
	kubectl delete -f .chart/manifests-gke/postgresql.yaml -n $(GKE_NAMESPACE)
	kubectl delete -f .chart/manifests-gke/service.yaml -n $(GKE_NAMESPACE)
	kubectl delete -f .chart/manifests-gke/deployment.yaml -n $(GKE_NAMESPACE)

.PHONY: deploy-gke push-gke clean-gke