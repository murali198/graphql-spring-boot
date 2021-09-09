helm dep update
helm upgrade --install --wait --atomic --timeout 500s rest-test . -n test-domain-dev -f values-dev.yaml --set-string image.tag=latest
