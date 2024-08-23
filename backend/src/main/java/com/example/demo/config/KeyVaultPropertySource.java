// package com.example.demo.config;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.core.env.PropertySource;

// import com.azure.security.keyvault.secrets.SecretClient;

// public class KeyVaultPropertySource extends PropertySource<SecretClient> {

//     private final Map<String, Object> properties = new HashMap<>();

//     public KeyVaultPropertySource(SecretClient secretClient) {
//         super("azureKeyVault");
//         loadSecrets(secretClient);
//     }

//     private void loadSecrets(SecretClient secretClient) {
//         properties.put("spring.datasource.url", secretClient.getSecret("DbConnectionString").getValue());
//         properties.put("spring.datasource.username", secretClient.getSecret("DbUsername").getValue());
//         properties.put("spring.datasource.password", secretClient.getSecret("DbPassword").getValue());
//     }

//     @Override
//     public Object getProperty(String name) {
//         return properties.get(name);
//     }
// }