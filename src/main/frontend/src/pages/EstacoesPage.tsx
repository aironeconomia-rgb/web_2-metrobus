import { useEffect, useState } from "react";
import api from "../services/api";
import type { Estacao, EstacaoFormData } from "../types/Estacao";
import EstacaoForm from "../components/EstacaoForm";
import EstacaoItem from "../components/EstacaoItem";

function EstacoesPage() {
  const [estacoes, setEstacoes] = useState<Estacao[]>([]);
  const [editando, setEditando] = useState<Estacao | null>(null);
  const [loading, setLoading] = useState(true);
  const [erro, setErro] = useState("");

  async function carregar() {
    try { setLoading(true); setErro(""); const r = await api.get<Estacao[]>("/estacoes"); setEstacoes(r.data); }
    catch { setErro("Não foi possível carregar as estações. Verifique se o back-end está ligado."); }
    finally { setLoading(false); }
  }
  useEffect(() => { carregar(); }, []);

  async function salvar(dados: EstacaoFormData) {
    try {
      if (editando) await api.put(`/estacoes/${editando.id}`, dados); else await api.post("/estacoes", dados);
      setEditando(null); await carregar();
    } catch (e: any) { alert(e?.response?.data?.message ?? "Erro ao salvar estação."); }
  }
  async function excluir(id: number) { if (!confirm("Excluir esta estação?")) return; await api.delete(`/estacoes/${id}`); await carregar(); }

  return <section><h1>Estações</h1><EstacaoForm estacaoEditando={editando} onSalvar={salvar} onCancelar={()=>setEditando(null)}/>{loading?<p>Carregando...</p>:erro?<p className="error">{erro}</p>:<div className="table-wrap"><table><thead><tr><th>ID</th><th>Código</th><th>Nome</th><th>Endereço</th><th>Capacidade</th><th>Status</th><th>Ações</th></tr></thead><tbody>{estacoes.map(e=><EstacaoItem key={e.id} estacao={e} onEditar={setEditando} onExcluir={excluir}/>)}</tbody></table></div>}</section>;
}
export default EstacoesPage;
