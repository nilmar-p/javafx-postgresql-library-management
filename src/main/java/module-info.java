module librarymanagement.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http; // Para GoogleBooksAPI

    requires org.kordamp.bootstrapfx.core; // Se estiver usando BootstrapFX
    requires com.fasterxml.jackson.databind; // Para processar JSON da API
    requires com.fasterxml.jackson.core;
    requires java.desktop;     // Dependência do Jackson

    // Abre o pacote principal para FXML (se necessário)
    opens librarymanagement.demo to javafx.fxml;
    exports librarymanagement.demo; // Exporta o pacote principal

    // Abre o pacote controller para FXML (para injeção e chamadas de método)
    opens controller to javafx.fxml;
    exports controller; // Exporta o pacote controller

    // --- Linhas Adicionadas ---
    opens model to javafx.base; // << IMPORTANTE: Permite reflexão do PropertyValueFactory
    exports model;              // << BOA PRÁTICA: Torna a classe Book acessível
    // --------------------------
}