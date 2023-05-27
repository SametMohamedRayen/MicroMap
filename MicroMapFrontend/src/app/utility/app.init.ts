import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService) {
    return () =>
      keycloak.init({
        config: {
          url: 'http://localhost:8080',
          realm: 'MICROMAP',
          clientId: 'angular-client'
          
        },
        
        initOptions: {
         
          onLoad: 'check-sso',
        checkLoginIframe: false,
        enableLogging: true,
         // Include user profile including roles
       
        },
        loadUserProfileAtStartUp: true

        
      });
  }