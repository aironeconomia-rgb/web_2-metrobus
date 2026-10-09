import { FormEvent, useEffect, useState } from "react";
import type { Motorista, MotoristaFormData } from "../types/Motorista";
interface Props { motoristaEditando: Motorista | null; onSalvar: (dados: MotoristaFormData) => Promise<void>; onCancelar: () => void; }
const vazio: MotoristaFormData = { nome:"", cpf:"", matricula:"", dataAdmissao:"", categoriaCnh:"", ativo:true };
function MotoristaForm({ motoristaEditando, onSalvar, onCancelar }: Props) {
  const [form, setForm] = useState<MotoristaFormData>(vazio);
  useEffect(() => setForm(motoristaEditando ? { nome:motoristaEditando.nome, cpf:motoristaEditando.cpf, matricula:motoristaEditando.matricula, dataAdmissao:motoristaEditando.dataAdmissao, categoriaCnh:motoristaEditando.categoriaCnh, ativo:motoristaEditando.ativo } : vazio), [motoristaEditando]);
  async function submit(e:FormEvent){e.preventDefault();await onSalvar(form);if(!motoristaEditando)setForm(vazio);}
  return <form className="form-card" onSubmit={submit}><h2>{motoristaEditando ? "Editar motorista" : "Novo motorista"}</h2><div className="form-grid">
    <label>Nome<input required value={form.nome} onChange={e=>setForm({...form,nome:e.target.value})}/></label>
    <label>CPF<input required value={form.cpf} onChange={e=>setForm({...form,cpf:e.target.value})}/></label>
    <label>Matrícula<input required value={form.matricula} onChange={e=>setForm({...form,matricula:e.target.value})}/></label>
    <label>Data de admissão<input type="date" required value={form.dataAdmissao} onChange={e=>setForm({...form,dataAdmissao:e.target.value})}/></label>
    <label>Categoria CNH<input required value={form.categoriaCnh} onChange={e=>setForm({...form,categoriaCnh:e.target.value})}/></label>
    <label className="checkbox"><input type="checkbox" checked={form.ativo} onChange={e=>setForm({...form,ativo:e.target.checked})}/> Ativo</label>
  </div><div className="actions"><button type="submit">Salvar</button>{motoristaEditando&&<button type="button" className="secondary" onClick={onCancelar}>Cancelar</button>}</div></form>;
}
export default MotoristaForm;
