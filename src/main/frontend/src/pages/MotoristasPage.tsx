import { useEffect, useState } from "react";
import api from "../services/api";
import type { Motorista, MotoristaFormData } from "../types/Motorista";
import MotoristaForm from "../components/MotoristaForm";
import MotoristaItem from "../components/MotoristaItem";
function MotoristasPage(){
 const[dados,setDados]=useState<Motorista[]>([]);const[editando,setEditando]=useState<Motorista|null>(null);const[loading,setLoading]=useState(true);const[erro,setErro]=useState("");
 async function carregar(){try{setLoading(true);setErro("");const r=await api.get<Motorista[]>("/motoristas");setDados(r.data);}catch{setErro("Não foi possível carregar os motoristas.");}finally{setLoading(false);}}
 useEffect(()=>{carregar();},[]);
 async function salvar(d:MotoristaFormData){try{editando?await api.put(`/motoristas/${editando.id}`,d):await api.post("/motoristas",d);setEditando(null);await carregar();}catch(e:any){alert(e?.response?.data?.message??"Erro ao salvar motorista.");}}
 async function excluir(id:number){if(!confirm("Excluir este motorista?"))return;await api.delete(`/motoristas/${id}`);await carregar();}
 return <section><h1>Motoristas</h1><MotoristaForm motoristaEditando={editando} onSalvar={salvar} onCancelar={()=>setEditando(null)}/>{loading?<p>Carregando...</p>:erro?<p className="error">{erro}</p>:<div className="table-wrap"><table><thead><tr><th>ID</th><th>Nome</th><th>CPF</th><th>Matrícula</th><th>Admissão</th><th>CNH</th><th>Status</th><th>Ações</th></tr></thead><tbody>{dados.map(x=><MotoristaItem key={x.id} motorista={x} onEditar={setEditando} onExcluir={excluir}/>)}</tbody></table></div>}</section>;
}
export default MotoristasPage;
