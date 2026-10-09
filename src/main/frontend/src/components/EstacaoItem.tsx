import type { Estacao } from "../types/Estacao";

interface Props {
  estacao: Estacao;
  onEditar: (estacao: Estacao) => void;
  onExcluir: (id: number) => void;
}

function EstacaoItem({ estacao, onEditar, onExcluir }: Props) {
  return (
    <tr>
      <td>{estacao.id}</td>
      <td>{estacao.codigoestacao}</td>
      <td>{estacao.nome}</td>
      <td>{estacao.endereco}</td>
      <td>{estacao.capacidadeMediaUsuarios ?? "-"}</td>
      <td>{estacao.ativa ? "Ativa" : "Inativa"}</td>
      <td className="actions">
        <button onClick={() => onEditar(estacao)}>Editar</button>
        <button className="danger" onClick={() => onExcluir(estacao.id)}>Excluir</button>
      </td>
    </tr>
  );
}

export default EstacaoItem;
