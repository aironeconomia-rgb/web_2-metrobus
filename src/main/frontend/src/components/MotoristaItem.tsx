import type { Motorista } from "../types/Motorista";
interface Props { motorista: Motorista; onEditar: (motorista: Motorista) => void; onExcluir: (id: number) => void; }
function MotoristaItem({ motorista, onEditar, onExcluir }: Props) {
  return <tr><td>{motorista.id}</td><td>{motorista.nome}</td><td>{motorista.cpf}</td><td>{motorista.matricula}</td><td>{motorista.dataAdmissao}</td><td>{motorista.categoriaCnh}</td><td>{motorista.ativo ? "Ativo" : "Inativo"}</td><td className="actions"><button onClick={() => onEditar(motorista)}>Editar</button><button className="danger" onClick={() => onExcluir(motorista.id)}>Excluir</button></td></tr>;
}
export default MotoristaItem;
