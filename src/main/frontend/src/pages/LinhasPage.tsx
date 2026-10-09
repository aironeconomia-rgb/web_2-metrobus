import { useEffect, useState } from "react";
import api from "../services/api";
import type { Linha, LinhaFormData } from "../types/Linha";
import LinhaForm from "../components/LinhaForm";
import LinhaItem from "../components/LinhaItem";
function LinhasPage(){
 const[linhas,setLinhas]=useState<Linha[]>([]);const[editando,setEditando]=useState<Linha|null>(null);const[loading,setLoading]=useState(true);const[erro,setErro]=useState("");
 async function carregar(){try{setLoading(true);setErro("");const r=await api.get<Linha[]>("/linhas");setLinhas(r.data);}catch{setErro("Não foi possível carregar as linhas.");}finally{setLoading(false);}}
 useEffect(()=>{carregar();},[]);
 async function salvar(d:LinhaFormData){try{editando?await api.put(`/linhas/${editando.id}`,d):await api.post("/linhas",d);setEditando(null);await carregar();}catch(e:any){alert(e?.response?.data?.message??"Erro ao salvar linha.");}}
 async function excluir(id:number){if(!confirm("Excluir esta linha?"))return;await api.delete(`/linhas/${id}`);await carregar();}
 return <section><h1>Linhas</h1><LinhaForm linhaEditando={editando} onSalvar={salvar} onCancelar={()=>setEditando(null)}/>{loading?<p>Carregando...</p>:erro?<p className="error">{erro}</p>:<div className="table-wrap"><table><thead><tr><th>ID</th><th>Código</th><th>Nome</th><th>Origem</th><th>Destino</th><th>Status</th><th>Ações</th></tr></thead><tbody>{linhas.map(x=><LinhaItem key={x.id} linha={x} onEditar={setEditando} onExcluir={excluir}/>)}</tbody></table></div>}</section>;
}
export default LinhasPage;
