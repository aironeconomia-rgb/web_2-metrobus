import { FormEvent, useEffect, useState } from "react";
import type { Estacao, EstacaoFormData } from "../types/Estacao";

interface Props {
  estacaoEditando: Estacao | null;
  onSalvar: (dados: EstacaoFormData) => Promise<void>;
  onCancelar: () => void;
}

const vazio: EstacaoFormData = {
  nome: "",
  codigoestacao: 0,
  endereco: "",
  capacidadeMediaUsuarios: 0,
  ativa: true,
};

function EstacaoForm({ estacaoEditando, onSalvar, onCancelar }: Props) {
  const [form, setForm] = useState<EstacaoFormData>(vazio);

  useEffect(() => {
    setForm(estacaoEditando ? {
      nome: estacaoEditando.nome,
      codigoestacao: estacaoEditando.codigoestacao,
      endereco: estacaoEditando.endereco,
      capacidadeMediaUsuarios: estacaoEditando.capacidadeMediaUsuarios,
      ativa: estacaoEditando.ativa,
    } : vazio);
  }, [estacaoEditando]);

  async function submit(event: FormEvent) {
    event.preventDefault();
    await onSalvar(form);
    if (!estacaoEditando) setForm(vazio);
  }

  return (
    <form className="form-card" onSubmit={submit}>
      <h2>{estacaoEditando ? "Editar estação" : "Nova estação"}</h2>
      <div className="form-grid">
        <label>Código<input type="number" required value={form.codigoestacao} onChange={e => setForm({ ...form, codigoestacao: Number(e.target.value) })} /></label>
        <label>Nome<input required value={form.nome} onChange={e => setForm({ ...form, nome: e.target.value })} /></label>
        <label>Endereço<input required value={form.endereco} onChange={e => setForm({ ...form, endereco: e.target.value })} /></label>
        <label>Capacidade<input type="number" value={form.capacidadeMediaUsuarios ?? 0} onChange={e => setForm({ ...form, capacidadeMediaUsuarios: Number(e.target.value) })} /></label>
        <label className="checkbox"><input type="checkbox" checked={form.ativa} onChange={e => setForm({ ...form, ativa: e.target.checked })} /> Ativa</label>
      </div>
      <div className="actions">
        <button type="submit">Salvar</button>
        {estacaoEditando && <button type="button" className="secondary" onClick={onCancelar}>Cancelar</button>}
      </div>
    </form>
  );
}

export default EstacaoForm;
