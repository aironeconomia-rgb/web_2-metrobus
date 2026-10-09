import { FormEvent, useEffect, useState } from "react";
import type { Veiculo, VeiculoFormData } from "../types/Veiculo";
interface Props { veiculoEditando: Veiculo | null; onSalvar: (dados: VeiculoFormData) => Promise<void>; onCancelar: () => void; }
const vazio: VeiculoFormData = { numeroFrota:0, placa:"", modelo:"", fabricante:"", dataDeFabricacao:"", capacidadePassageiros:0, consumoPorKM:0, ativo:true };
function VeiculoForm({ veiculoEditando, onSalvar, onCancelar }: Props){
 const[form,setForm]=useState<VeiculoFormData>(vazio);
 useEffect(()=>setForm(veiculoEditando?{numeroFrota:veiculoEditando.numeroFrota,placa:veiculoEditando.placa,modelo:veiculoEditando.modelo,fabricante:veiculoEditando.fabricante,dataDeFabricacao:veiculoEditando.dataDeFabricacao,capacidadePassageiros:veiculoEditando.capacidadePassageiros,consumoPorKM:veiculoEditando.consumoPorKM,ativo:veiculoEditando.ativo}:vazio),[veiculoEditando]);
 async function submit(e:FormEvent){e.preventDefault();await onSalvar(form);if(!veiculoEditando)setForm(vazio);}
 return <form className="form-card" onSubmit={submit}><h2>{veiculoEditando?"Editar veículo":"Novo veículo"}</h2><div className="form-grid">
  <label>Nº frota<input type="number" required value={form.numeroFrota} onChange={e=>setForm({...form,numeroFrota:Number(e.target.value)})}/></label>
  <label>Placa<input required value={form.placa} onChange={e=>setForm({...form,placa:e.target.value})}/></label>
  <label>Fabricante<input required value={form.fabricante} onChange={e=>setForm({...form,fabricante:e.target.value})}/></label>
  <label>Modelo<input required value={form.modelo} onChange={e=>setForm({...form,modelo:e.target.value})}/></label>
  <label>Data de fabricação<input type="date" required value={form.dataDeFabricacao} onChange={e=>setForm({...form,dataDeFabricacao:e.target.value})}/></label>
  <label>Capacidade<input type="number" required value={form.capacidadePassageiros} onChange={e=>setForm({...form,capacidadePassageiros:Number(e.target.value)})}/></label>
  <label>Consumo por KM<input type="number" step="0.01" required value={form.consumoPorKM} onChange={e=>setForm({...form,consumoPorKM:Number(e.target.value)})}/></label>
  <label className="checkbox"><input type="checkbox" checked={form.ativo} onChange={e=>setForm({...form,ativo:e.target.checked})}/> Ativo</label>
 </div><div className="actions"><button type="submit">Salvar</button>{veiculoEditando&&<button type="button" className="secondary" onClick={onCancelar}>Cancelar</button>}</div></form>;
}
export default VeiculoForm;
