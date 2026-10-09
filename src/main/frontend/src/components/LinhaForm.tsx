import { FormEvent, useEffect, useState } from "react";
import type { Linha, LinhaFormData } from "../types/Linha";

interface Props { linhaEditando: Linha | null; onSalvar: (dados: LinhaFormData) => Promise<void>; onCancelar: () => void; }
const vazio: LinhaFormData = { codigolinha: 0, nome: "", origem: "", destino: "", ativa: true };

function LinhaForm({ linhaEditando, onSalvar, onCancelar }: Props) {
  const [form, setForm] = useState<LinhaFormData>(vazio);
  useEffect(() => setForm(linhaEditando ? { codigolinha: linhaEditando.codigolinha, nome: linhaEditando.nome, origem: linhaEditando.origem, destino: linhaEditando.destino, ativa: linhaEditando.ativa } : vazio), [linhaEditando]);
  async function submit(e: FormEvent) { e.preventDefault(); await onSalvar(form); if (!linhaEditando) setForm(vazio); }
  return <form className="form-card" onSubmit={submit}>
    <h2>{linhaEditando ? "Editar linha" : "Nova linha"}</h2>
    <div className="form-grid">
      <label>Código<input type="number" required value={form.codigolinha} onChange={e => setForm({...form, codigolinha:Number(e.target.value)})}/></label>
      <label>Nome<input required value={form.nome} onChange={e => setForm({...form, nome:e.target.value})}/></label>
      <label>Origem<input required value={form.origem} onChange={e => setForm({...form, origem:e.target.value})}/></label>
      <label>Destino<input required value={form.destino} onChange={e => setForm({...form, destino:e.target.value})}/></label>
      <label className="checkbox"><input type="checkbox" checked={form.ativa} onChange={e => setForm({...form, ativa:e.target.checked})}/> Ativa</label>
    </div>
    <div className="actions"><button type="submit">Salvar</button>{linhaEditando && <button type="button" className="secondary" onClick={onCancelar}>Cancelar</button>}</div>
  </form>;
}
export default LinhaForm;
