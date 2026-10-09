import type { Linha } from "../types/Linha";

interface Props {
  linha: Linha;
  onEditar: (linha: Linha) => void;
  onExcluir: (id: number) => void;
}

function LinhaItem({ linha, onEditar, onExcluir }: Props) {
  return (
    <tr>
      <td>{linha.id}</td><td>{linha.codigolinha}</td><td>{linha.nome}</td><td>{linha.origem}</td><td>{linha.destino}</td><td>{linha.ativa ? "Ativa" : "Inativa"}</td>
      <td className="actions"><button onClick={() => onEditar(linha)}>Editar</button><button className="danger" onClick={() => onExcluir(linha.id)}>Excluir</button></td>
    </tr>
  );
}
export default LinhaItem;
