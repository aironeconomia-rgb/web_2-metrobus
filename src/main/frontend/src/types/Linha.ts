export interface Linha {
  id: number;
  codigolinha: number;
  nome: string;
  origem: string;
  destino: string;
  ativa: boolean;
}

export type LinhaFormData = Omit<Linha, "id">;
