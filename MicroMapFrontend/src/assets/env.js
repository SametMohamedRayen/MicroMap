(function(window) {
    window["env"] = window["env"] || {};
  
    // Environment variables
    window["env"]["apiUrl"] = '${BACKEND}';
    
    window["env"]["clientId"] = '${CLIENT_ID}';
   window["env"]["keycloakUrl"] = '${KEYCLOAK_URL}';
   window["env"]["realm"] = '${REALM}';
    
  })(this);