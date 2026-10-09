export interface Motorista {
  id: number;
  nome: string;
  cpf: string;
  matricula: string;
  dataAdmissao: string;
  categoriaCnh: string;
  ativo: boolean;
}

export type MotoristaFormData = Omit<Motorista, "id">;
