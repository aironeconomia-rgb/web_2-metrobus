export interface Estacao {
  id: number;
  nome: string;
  codigoestacao: number;
  endereco: string;
  capacidadeMediaUsuarios: number | null;
  ativa: boolean;
}

export type EstacaoFormData = Omit<Estacao, "id">;
