# OpenSearch Kafka Consumer Example

* Add dependencies.
* Create docker compose to start OpenSearch.
```dockerfile
version: '2'
services:
  opensearch:
    image: opensearchproject/opensearch:1.2.4
    environment:
      discover.type: single-node
      plugins.security.disabled: true
      compatibility.override_main_response_version: true
    ports:
      - "9200:9200"
      - "9600:9600"

  opensearch-dashboards:
    image: opensearchproject/opensearch-dashboards:1.2.0
    ports:
      - "5601:5601"
    environment:
      OPENSEARCH_HOST: '["http://opensearch:9200"]'
      DISABLE_SECURITY_DASHBOARDS_PLUGIN: "true"
```

* Check 