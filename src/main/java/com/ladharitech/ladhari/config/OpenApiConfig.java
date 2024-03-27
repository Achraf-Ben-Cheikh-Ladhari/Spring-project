package com.ladharitech.ladhari.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name="Achraf Ladhari",
                email="achraf.bencheikhladhari@polytechnicien.tn"
        ),
        description = "OpenAPI doc ladhari tech",
        title = "OpenAPI spec - Achraf LADHARI",
        version = "1.0",
        termsOfService = "Terms of service..."
    ),
        servers = {
        @Server(
                description = "Local ENV",
                url = "http://localhost:3000"
        ),
                @Server(
                        description = "PROD ENV",
                        url = "http://achrafladhari.c1.biz"
                )
        }
)
@SecurityScheme(
        name="bearerAuth",
        description = "JWT auth",
        scheme = "bearer",
        type= SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
    )
public class OpenApiConfig {
}
