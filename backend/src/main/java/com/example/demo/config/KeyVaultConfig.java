// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.env.ConfigurableEnvironment;
// import org.springframework.core.env.PropertySource;

// import com.azure.identity.DefaultAzureCredentialBuilder;
// import com.azure.security.keyvault.secrets.SecretClient;
// import com.azure.security.keyvault.secrets.SecretClientBuilder;

// @Configuration
// public class KeyVaultConfig {

//     @Bean
//     public SecretClient secretClient() {
//         return new SecretClientBuilder()
//             .vaultUrl("https://angularjavaspringdemokey.vault.azure.net/")
//             .credential(new DefaultAzureCredentialBuilder().build())
//             .buildClient();
//     }

//     @Bean
//     public PropertySource<?> keyVaultPropertySource(SecretClient secretClient, ConfigurableEnvironment environment) {
//         KeyVaultPropertySource keyVaultPropertySource = new KeyVaultPropertySource(secretClient);
//         environment.getPropertySources().addFirst(keyVaultPropertySource); // Ensures high precedence
//         return keyVaultPropertySource;
//     }
// }