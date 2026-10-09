import { useEffect, useState } from "react";
import api from "../services/api";
import type { Veiculo, VeiculoFormData } from "../types/Veiculo";
import VeiculoForm from "../components/VeiculoForm";
import VeiculoItem from "../components/VeiculoItem";
function VeiculosPage(){
 const[dados,setDados]=useState<Veiculo[]>([]);const[editando,setEditando]=useState<Veiculo|null>(null);const[loading,setLoading]=useState(true);const[erro,setErro]=useState("");
 async function carregar(){try{setLoading(true);setErro("");const r=await api.get<Veiculo[]>("/veiculos");setDados(r.data);}catch{setErro("Não foi possível carregar os veículos.");}finally{setLoading(false);}}
 useEffect(()=>{carregar();},[]);
 async function salvar(d:VeiculoFormData){try{editando?await api.put(`/veiculos/${editando.id}`,d):await api.post("/veiculos",d);setEditando(null);await carregar();}catch(e:any){alert(e?.response?.data?.message??"Erro ao salvar veículo.");}}
 async function excluir(id:number){if(!confirm("Excluir este veículo?"))return;await api.delete(`/veiculos/${id}`);await carregar();}
 return <section><h1>Veículos</h1><VeiculoForm veiculoEditando={editando} onSalvar={salvar} onCancelar={()=>setEditando(null)}/>{loading?<p>Carregando...</p>:erro?<p className="error">{erro}</p>:<div className="table-wrap"><table><thead><tr><th>ID</th><th>Frota</th><th>Placa</th><th>Fabricante</th><th>Modelo</th><th>Capacidade</th><th>Status</th><th>Ações</th></tr></thead><tbody>{dados.map(x=><VeiculoItem key={x.id} veiculo={x} onEditar={setEditando} onExcluir={excluir}/>)}</tbody></table></div>}</section>;
}
export default VeiculosPage;
