import { useState } from "react";
import EstacoesPage from "./pages/EstacoesPage";
import LinhasPage from "./pages/LinhasPage";
import MotoristasPage from "./pages/MotoristasPage";
import VeiculosPage from "./pages/VeiculosPage";
import "./styles.css";

type Pagina = "estacoes" | "linhas" | "motoristas" | "veiculos";

function App() {
  const [pagina, setPagina] = useState<Pagina>("estacoes");

  return (
    <div className="app-shell">
      <header>
        <div><strong>Metrobus</strong><span>Programação Web II</span></div>
        <nav>
          <button className={pagina === "estacoes" ? "active" : ""} onClick={() => setPagina("estacoes")}>Estações</button>
          <button className={pagina === "linhas" ? "active" : ""} onClick={() => setPagina("linhas")}>Linhas</button>
          <button className={pagina === "motoristas" ? "active" : ""} onClick={() => setPagina("motoristas")}>Motoristas</button>
          <button className={pagina === "veiculos" ? "active" : ""} onClick={() => setPagina("veiculos")}>Veículos</button>
        </nav>
      </header>
      <main>
        {pagina === "estacoes" && <EstacoesPage />}
        {pagina === "linhas" && <LinhasPage />}
        {pagina === "motoristas" && <MotoristasPage />}
        {pagina === "veiculos" && <VeiculosPage />}
      </main>
    </div>
  );
}

export default App;
