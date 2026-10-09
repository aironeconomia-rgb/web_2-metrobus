import type { Veiculo } from "../types/Veiculo";
interface Props { veiculo: Veiculo; onEditar: (veiculo: Veiculo) => void; onExcluir: (id: number) => void; }
function VeiculoItem({ veiculo, onEditar, onExcluir }: Props) {
 return <tr><td>{veiculo.id}</td><td>{veiculo.numeroFrota}</td><td>{veiculo.placa}</td><td>{veiculo.fabricante}</td><td>{veiculo.modelo}</td><td>{veiculo.capacidadePassageiros}</td><td>{veiculo.ativo?"Ativo":"Inativo"}</td><td className="actions"><button onClick={()=>onEditar(veiculo)}>Editar</button><button className="danger" onClick={()=>onExcluir(veiculo.id)}>Excluir</button></td></tr>;
}
export default VeiculoItem;
