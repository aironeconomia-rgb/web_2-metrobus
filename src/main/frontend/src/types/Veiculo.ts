export interface Veiculo {
  id: number;
  numeroFrota: number;
  placa: string;
  modelo: string;
  fabricante: string;
  dataDeFabricacao: string;
  capacidadePassageiros: number;
  consumoPorKM: number;
  ativo: boolean;
}

export type VeiculoFormData = Omit<Veiculo, "id">;
