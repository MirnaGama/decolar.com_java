package menu;

import java.util.Scanner;

import classesIniciais.Cliente;
import classesIniciais.Hotel;
import classesIniciais.Quarto;
import classesIniciais.Reserva;
import classesIniciais.Voo;
import exceptions.ClienteJaCadastradoException;
import exceptions.CpfCadastradoEmOutroClienteException;
import exceptions.CpfNaoCadastradoException;
import exceptions.CpfNaoInformadoException;
import exceptions.DataDoVooNaoInformadaException;
import exceptions.DestinoDoVooNaoInformadoException;
import exceptions.HotelJaCadastradoException;
import exceptions.HotelNaoCadastradoException;
import exceptions.IdadeNaoPermitidaException;
import exceptions.LocalNaoEncontradoException;
import exceptions.LocalNaoInformadoException;
import exceptions.NaoHaHoteisCadastradosException;
import exceptions.NaoHaVoosCadastradosException;
import exceptions.NomeHotelNaoInformadoException;
import exceptions.NumeroDoVooNaoInformadoException;
import exceptions.OrigemDoVooNaoInformadaException;
import exceptions.QuartoJaCadastradoException;
import exceptions.ResultadoNaoEncontradoException;
import exceptions.VooJaCadastradoException;
import exceptions.VooNaoCadastradoException;
import fachada.Fachada;
import repositorios.RepositorioQuartoArrayList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Menu {

	public void menuInicial(Scanner sc, Fachada fachada, DataMenu data) {
		boolean sair = false;
		int resposta = 0;
		boolean opcaoValida = false;
		Cliente c = null;
		System.out.println("Olá! :) \nPara começar a utilizar o sistema, escolha uma das opções abaixo!");
		while (!sair) {
			while (!opcaoValida) {
				System.out.println();
				System.out.println("1) Gerenciar o sistema");
				System.out.println("2) Buscar voo ou hotel");
				System.out.println("0) Sair");
				try {
					resposta = Integer.parseInt(sc.next());
					opcaoValida = true;
				} catch (Exception e) {
					System.out.println("Ops! Parece que você não inseriu uma opção válida. Tente novamente.");
					System.out.println();
					menuInicial(sc, fachada, data);
				}

				if (resposta == 1) {
					gerenciarSistema(sc, fachada, data);
				} else if (resposta == 2) {
					boolean result = false;
					int idade = 0;

					System.out.println("Informe seu cpf: ");
					sc = new Scanner(System.in);
					String cpf = sc.nextLine();
					if (fachada.existeCpf(cpf)) {
						try {
							c = fachada.procurarCliente(cpf);
							System.out.println("Bem-vindo(a) " + c.getNome() + "!");
							menu(sc, fachada, data);
						} catch (CpfNaoCadastradoException cpfNaoCadastradoException) {
							System.out.println(cpfNaoCadastradoException.getMessage());
						} catch (CpfNaoInformadoException cpfNaoInformadoException) {
							System.out.println(cpfNaoInformadoException.getMessage());
						}

					} else {
						System.out.println("Cliente não cadastrado.");
						while (!result) {
							System.out.println("Deseja fazer um cadastro no Decolar.com?");
							sc = new Scanner(System.in);
							String resposta2 = sc.nextLine();
							if (resposta2.equalsIgnoreCase("sim")) {
								boolean nomeValido = false;
								String nome = null;
								while (!nomeValido) {
									System.out.println("Informe seu nome: ");
									sc = new Scanner(System.in);
									nome = sc.nextLine();
									nome = nome.trim();
									if (!nome.isEmpty()) {
										nomeValido = true;

									} else {
										System.out.println("Nome inválido. Por favor, informe-o novamente.");
									}
								}
								String cpf2 = null;
								boolean cpfValido = false;
								while (!cpfValido) {
									System.out.println("Informe seu cpf: ");
									sc = new Scanner(System.in);
									cpf2 = sc.nextLine();
									cpf2 = cpf2.trim();
									if (!cpf2.isEmpty()) {
										cpfValido = true;

									} else {
										System.out.println("Cpf inválido. Por favor, informe-o novamente.");
									}
								}
								if (!fachada.existeCpf(cpf2)) {
									boolean idadeValida = false;
									while(!idadeValida) {
									System.out.println("Informe sua idade: ");
									try {
										idade = Integer.parseInt(sc.next());
										idadeValida = true;
									} catch (Exception e) {
										System.out.println("Você não inseriu uma idade válida. Tente novamente.");
									}
									}

									Cliente cliente = new Cliente(nome, cpf, idade);

									try {
										fachada.inserirCliente(cliente);
										System.out.println("Cadastrado com sucesso!");
										System.out.println("Bem-vindo(a) " + cliente.getNome() + "!");
										menu(sc, fachada, data);
									} catch (ClienteJaCadastradoException clienteJaCadastradoException) {
										System.out.println(clienteJaCadastradoException.getMessage());
									} catch (IdadeNaoPermitidaException idadeNaoPermitidaException) {
										System.out.println(idadeNaoPermitidaException.getMessage());
									} catch (CpfNaoInformadoException cpfNaoInformadoException) {
										System.out.println(cpfNaoInformadoException.getMessage());
									} catch (CpfCadastradoEmOutroClienteException cpfCadastradoEmOutroClienteException) {
										System.out.println(cpfCadastradoEmOutroClienteException.getMessage());
									}
								} else {
									System.out.println("Cpf já cadastrado.");
								}
							} else if (resposta2.equalsIgnoreCase("nao") || resposta2.equalsIgnoreCase("não")) {
								result = true;
								System.out.println("Poxa... Que pena! Deseja sair do sistema?");
								sc = new Scanner(System.in);
								String resposta3 = sc.nextLine();
								if (resposta3.equalsIgnoreCase("sim")) {
									System.out.println("Obrigada pela visita! Até a próxima... =)");
									System.exit(0);
								} else if (resposta3.equalsIgnoreCase("nao") || resposta3.equalsIgnoreCase("não")) {
									menuInicial(sc, fachada, data);
								} else {
									System.out.println("Opção inválida. Tente novamente.");
									System.out.println();
									menuInicial(sc, fachada, data);
								}

							} else {
								System.out.println("Opção inválida. Tente novamente.");
								System.out.println();
								menuInicial(sc, fachada, data);
							}
						}
					}
				} else if (resposta == 0) {
					System.out.println("Obrigada pela visita! Até a próxima... =)");
					sair = true;
				} else {
					System.out.println("Opção inválida. Tente novamente.");
					System.out.println();
					menuInicial(sc, fachada, data);
				}
			}
		}
	}

	public void gerenciarSistema(Scanner sc, Fachada fachada, DataMenu data) {
		System.out.println();
		int escolha = 0;
		int opcao = 0;
		System.out.println("1) Gerenciar voos");
		System.out.println("2) Gerenciar hotéis");
		System.out.println("3) Voltar ao menu principal");
		System.out.println("4) Sair");
		try {
			opcao = Integer.parseInt(sc.next());
		} catch (Exception e) {

		}

		String data2 = null;
		int qtd = 0;

		if (opcao == 1) {
			System.out.println();
			System.out.println("1) Inserir");
			System.out.println("2) Remover");
			System.out.println("3) Atualizar");
			System.out.println("4) Procurar");
			System.out.println("5) Voltar ao menu anterior");
			try {
				escolha = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("Ops! Parece que você não inseriu uma opção válida. Tente novamente!");
				gerenciarSistema(sc, fachada, data);
			}
			boolean validacao = false;

			if (escolha == 1) {
				System.out.println("Informe o número do voo: ");
				sc = new Scanner(System.in);
				String numero = sc.nextLine();
				if (!fachada.existeNumeroDoVoo(numero)) {
					while (!validacao) {
						System.out.println("Informe a quantidade de passagens do voo " + numero + ":");
						try {
							qtd = Integer.parseInt(sc.next());
							validacao = true;
						} catch (Exception e) {
							System.out.println("Ops! Parece que você não inseriu uma nota válida. Tente novamente.");
							gerenciarSistema(sc, fachada, data);
						}
					}
					data2 = data.data();
					System.out.println("Informe a origem do voo " + numero + ":");
					sc = new Scanner(System.in);
					String origem = sc.nextLine();
					System.out.println("Informe o destino do voo " + numero + ":");
					sc = new Scanner(System.in);
					String destino = sc.nextLine();
					System.out.println("Informe o valor da passagem do voo " + numero + ":");
					double valor = sc.nextDouble();
					Voo voo = new Voo(numero, qtd, data2, origem, destino, valor);
					try {
						fachada.inserirVoo(voo);
						System.out.println("Voo adicionado com sucesso!");
						gerenciarSistema(sc, fachada, data);
					} catch (VooJaCadastradoException vjce) {
						System.out.println(vjce.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (DataDoVooNaoInformadaException dvni) {
						System.out.println(dvni.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (DestinoDoVooNaoInformadoException dvnie) {
						System.out.println(dvnie.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (NumeroDoVooNaoInformadoException nvnie) {
						System.out.println(nvnie.getMessage());
					} catch (OrigemDoVooNaoInformadaException ovnie) {
						System.out.println(ovnie.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (VooNaoCadastradoException vnce) {
						System.out.println(vnce.getMessage());
					}
				} else {
					System.out.println("Voo já cadastrado");
					gerenciarSistema(sc, fachada, data);
				}
			} else if (escolha == 2) {
				System.out.println("Insira o número do voo que você deseja remover: ");
				sc = new Scanner(System.in);
				String numero = sc.nextLine();

				Voo v = null;
				try {
					v = fachada.procurarVoo(numero);
				} catch (VooNaoCadastradoException vnce) {
					System.out.println(vnce.getMessage());
					gerenciarSistema(sc, fachada, data);
				} catch (NumeroDoVooNaoInformadoException nvni) {
					System.out.println(nvni.getMessage());
					gerenciarSistema(sc, fachada, data);
				}

				if (v != null) {
					try {
						fachada.removerVoo(v);
						System.out.println("Removido com sucesso!");
						gerenciarSistema(sc, fachada, data);
					} catch (VooNaoCadastradoException vnce) {
						System.out.println(vnce.getMessage());
						gerenciarSistema(sc, fachada, data);
					}
				}

			} else if (escolha == 3) {
				System.out.println("Insira o número do voo que você deseja atualizar: ");
				sc = new Scanner(System.in);
				String numero = sc.nextLine();

				Voo v = null;
				try {
					v = fachada.procurarVoo(numero);
				} catch (VooNaoCadastradoException vnce) {
					System.out.println(vnce.getMessage());
					gerenciarSistema(sc, fachada, data);
				} catch (NumeroDoVooNaoInformadoException nvni) {
					System.out.println(nvni.getMessage());
					gerenciarSistema(sc, fachada, data);
				}

				if (v != null) {
					try {
						fachada.atualizarVoo(v);
						System.out.println("Atualizado com sucesso!");
						gerenciarSistema(sc, fachada, data);
					} catch (VooNaoCadastradoException vnce) {
						System.out.println(vnce.getMessage());
						gerenciarSistema(sc, fachada, data);
					}
				}

			} else if (escolha == 4) {
				System.out.println("Insira o número do voo que você deseja procurar: ");
				sc = new Scanner(System.in);
				String numero = sc.nextLine();
				Voo v = null;

				try {
					v = fachada.procurarVoo(numero);
					System.out.println();
					System.out.println(v);
					gerenciarSistema(sc, fachada, data);
				} catch (VooNaoCadastradoException vnce) {
					System.out.println(vnce.getMessage());
					gerenciarSistema(sc, fachada, data);
				} catch (NumeroDoVooNaoInformadoException nvni) {
					System.out.println(nvni.getMessage());
					gerenciarSistema(sc, fachada, data);
				}
			} else if (escolha == 5) {
				gerenciarSistema(sc, fachada, data);
			}
		} else if (opcao == 2) {
			System.out.println();
			System.out.println("1) Inserir");
			System.out.println("2) Remover");
			System.out.println("3) Atualizar");
			System.out.println("4) Procurar");
			System.out.println("5) Voltar ao menu anterior");
			try {
				escolha = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("Ops! Parece que você não inseriu uma opção válida. Tente novamente!");
				gerenciarSistema(sc, fachada, data);
			}
			int quantidade = 0;

			if (escolha == 1) {
				System.out.println("Insira o nome do hotel: ");
				sc = new Scanner(System.in);
				String nome = sc.nextLine();
				if (!fachada.existeNome(nome)) {
					System.out.println("Insira o local do hotel " + nome + ":");
					sc = new Scanner(System.in);
					String local = sc.nextLine();
					System.out.println("Insira o valor da diária: ");
					double preco = sc.nextDouble();
					System.out.println("Insira a quantidade de quartos do hotel " + nome + ":");
					try {
						quantidade = Integer.parseInt(sc.next());
					} catch (Exception e) {
						System.out.println("Ops! Parece que você não inseriu uma opção válida. Tente novamente!");
					}

					RepositorioQuartoArrayList repositorioQuartoArrayList = new RepositorioQuartoArrayList(nome);
					Hotel hotel = new Hotel(nome, local, preco, quantidade, repositorioQuartoArrayList);

					for (int i = 1; i <= quantidade; i++) {
						Quarto quarto = new Quarto(hotel, i);
						try {
							hotel.getRepQuartos().inserir(quarto);
						} catch (QuartoJaCadastradoException quartoJaCadastradoException) {
							System.out.println(quartoJaCadastradoException.getMessage());
							gerenciarSistema(sc, fachada, data);
						}
					}

					try {
						fachada.inserirHotel(hotel);
						System.out.println("Hotel adicionado com sucesso!");
						gerenciarSistema(sc, fachada, data);
					} catch (HotelJaCadastradoException hjce) {
						System.out.println(hjce.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (NomeHotelNaoInformadoException nhnif) {
						System.out.println(nhnif.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (LocalNaoInformadoException lcie) {
						System.out.println(lcie.getMessage());
						gerenciarSistema(sc, fachada, data);
					}
				} else {
					System.out.println("Hotel já cadastrado.");
					gerenciarSistema(sc, fachada, data);
				}
			} else if (escolha == 2) {
				System.out.println("Insira o nome do hotel que você deseja remover: ");
				sc = new Scanner(System.in);
				String nome = sc.nextLine();

				Hotel h = null;
				try {
					h = fachada.procurarHotelPorNome(nome);
				} catch (HotelNaoCadastradoException hnce) {
					System.out.println(hnce.getMessage());
					gerenciarSistema(sc, fachada, data);
				}

				if (h != null) {
					try {
						fachada.removerHotel(h);
						System.out.println("Removido com sucesso!");
						gerenciarSistema(sc, fachada, data);
					} catch (HotelNaoCadastradoException hnce) {
						System.out.println(hnce.getMessage());
						gerenciarSistema(sc, fachada, data);
					}
				}
			} else if (escolha == 3) {
				System.out.println("Insira o nome do hotel que você deseja atualizar: ");
				sc = new Scanner(System.in);
				String nome = sc.nextLine();

				Hotel h = null;
				try {
					h = fachada.procurarHotelPorNome(nome);
				} catch (HotelNaoCadastradoException hnce) {
					System.out.println(hnce.getMessage());
					gerenciarSistema(sc, fachada, data);
				}

				if (h != null) {
					try {
						fachada.atualizarHotel(h);
						System.out.println("Atualizado com sucesso!");
						gerenciarSistema(sc, fachada, data);
					} catch (HotelNaoCadastradoException hnce) {
						System.out.println(hnce.getMessage());
						gerenciarSistema(sc, fachada, data);
					} catch (NomeHotelNaoInformadoException nomeHotelNaoInformadoException) {
						System.out.println(nomeHotelNaoInformadoException.getMessage());
						gerenciarSistema(sc, fachada, data);
					}
				}
			} else if (escolha == 4) {
				System.out.println("Insira o nome do hotel que você deseja procurar: ");
				sc = new Scanner(System.in);
				String nome = sc.nextLine();

				Hotel h = null;
				try {
					h = fachada.procurarHotelPorNome(nome);
					System.out.println();
					System.out.println(h);
					gerenciarSistema(sc, fachada, data);
				} catch (HotelNaoCadastradoException hnce) {
					System.out.println(hnce.getMessage());
					gerenciarSistema(sc, fachada, data);
				}

			} else if (escolha == 5) {
				gerenciarSistema(sc, fachada, data);
			}
		} else if (opcao == 3) {
			menuInicial(sc, fachada, data);
		} else if (opcao == 4) {
			System.out.println("Obrigada pela visita! Até a próxima... =)");
			System.exit(0);
		} else {
			System.out.println("Ops! Parece que você inseriu uma opção inválida. Tente novamente!");
			gerenciarSistema(sc, fachada, data);
		}
	}

	public void menu(Scanner sc, Fachada fachada, DataMenu data) {

		System.out.println();
		System.out.println("========Bem-vindo ao Decolar.com!===========");
		System.out.println("1) Procurar hotel ou voo");
		System.out.println("2) Fazer check-out");
		System.out.println("3) Ver relatórios");
		System.out.println("4) Voltar ao menu principal");
		System.out.println("5) Sair");
		int resposta = 0;
		try {
			resposta = Integer.parseInt(sc.next());
		} catch (Exception e) {
			System.out.println("Ops! Parece que você não inseriu uma opção válida. Tente novamente!");
			menu(sc, fachada, data);
		}

		if (resposta == 1) {
			procurar(sc, fachada, data);
		} else if (resposta == 2) {
			avaliar(sc, fachada, data);
		} else if (resposta == 3) {
			relatorios(sc, fachada, data);
		} else if (resposta == 4) {
			menuInicial(sc, fachada, data);
		} else if (resposta == 5) {
			System.out.println("Obrigada pela visita! Até a próxima... =)");
			System.exit(0);
		} else {
			System.out.println("Ops! Parece que você inseriu uma opção inválida. Tente novamente!");
			menu(sc, fachada, data);
		}
	}

	public void procurar(Scanner sc, Fachada fachada, DataMenu data) {
		System.out.println();
		System.out.println(
				"1) Buscar voo\n2) Buscar hotel\n3) Voltar ao menu anterior\n4) Voltar ao menu principal\n5) Sair");
		int resposta = 0;
		try {
			resposta = Integer.parseInt(sc.next());
		} catch (Exception e) {

		}

		if (resposta == 1) {
			System.out.println("Insira o destino que você deseja: ");
			sc = new Scanner(System.in);
			String destino = sc.nextLine();
			String cpf;
			Cliente c = null;
			if (fachada.buscarPorDestino(destino)) {
				System.out.println("Insira o número do voo que você deseja: ");
				sc = new Scanner(System.in);
				String numeroDoVoo = sc.nextLine();
				boolean result = false;
				String resposta2;
				int quantidade = 0;
				if (fachada.existeNumeroDoVoo(numeroDoVoo)) {
					Voo v = null;
					try {
						v = fachada.procurarVoo(numeroDoVoo);
						v.setDestinoMaisProcurado(1);
						v.setBuscas(1);
						while (!result) {
							System.out.println("Deseja comprar passagem?");
							sc = new Scanner(System.in);
							resposta2 = sc.nextLine();
							if (resposta2.equalsIgnoreCase("Sim")) {
								System.out.println("Insira seu cpf: ");
								sc = new Scanner(System.in);
								cpf = sc.nextLine();
								if (fachada.existeCpf(cpf)) {
									try {
										c = fachada.procurarCliente(cpf);
									} catch (CpfNaoCadastradoException cpfNaoCadastradoException) {
										System.out.println(cpfNaoCadastradoException.getMessage());
										menu(sc, fachada, data);
									} catch (CpfNaoInformadoException cpfNaoInformadoException) {
										System.out.println(cpfNaoInformadoException.getMessage());
										menu(sc, fachada, data);
									}
									System.out.println("Insira a quantidade de passagens que voce deseja comprar: ");
									try {
										quantidade = Integer.parseInt(sc.next());
									} catch (Exception e) {
										System.out.println(
												"Ops! Parece que você não inseriu uma quantidade válida. Tente novamente.");
									}
									if (quantidade <= 0) {
										System.out.println("Ops! Você não inseriu uma quantidade correta.");
										procurar(sc, fachada, data);
									}
									if (quantidade > v.getQtdPassagensDisponiveis()) {
										System.out.println("Não foi possível comprar esta quantidade de passagens.");
										procurar(sc, fachada, data);
									}
									if (v.getQtdPassagensDisponiveis() == 0) {
										v.setLotado();
										System.out.println("Voo lotado.");
										procurar(sc, fachada, data);
									}
									result = true;
									fachada.comprarPassagem(v, quantidade, c);
									procurar(sc, fachada, data);
								} else {
									System.out.println("Cpf não encontrado.");
									menu(sc, fachada, data);
								}

							} else if (resposta2.equalsIgnoreCase("Não") || resposta2.equalsIgnoreCase("Nao")) {
								result = true;
								procurar(sc, fachada, data);
							} else {
								System.out.println("Você inseriu uma resposta inválida. Tente novamente");
							}
						}

					} catch (VooNaoCadastradoException vnce) {
						System.out.println(vnce.getMessage());
						menu(sc, fachada, data);
					} catch (NumeroDoVooNaoInformadoException nvnie) {
						System.out.println(nvnie.getMessage());
						menu(sc, fachada, data);
					}
				} else {
					System.out.println("Voo não encontrado.");
					menu(sc, fachada, data);
				}
			} else {
				System.out.println("Destino não encontrado.");
				menu(sc, fachada, data);
			}
		} else if (resposta == 2) {
			System.out.println("Insira o local que você deseja");
			sc = new Scanner(System.in);
			String destino = sc.nextLine();
			String cpf;
			Cliente c = null;
			try {
				if (fachada.buscarPorLocal(destino)) {
					System.out.println("Insira o nome do Hotel desejado: ");
					sc = new Scanner(System.in);
					String nome = sc.nextLine();
					boolean result = false;
					int quantidade = 0;
					if (fachada.existeNome(nome)) {
						Hotel h = null;
						try {
							h = fachada.procurarHotelPorNome(nome);
							h.setLocalMaisProcurado(1);
							h.setBuscas(1);
							while (!result) {
								System.out.println("Insira seu cpf: ");
								sc = new Scanner(System.in);
								cpf = sc.nextLine();
								if (fachada.existeCpf(cpf)) {
									try {
										c = fachada.procurarCliente(cpf);
									} catch (CpfNaoCadastradoException cpfNaoCadastradoException) {
										System.out.println(cpfNaoCadastradoException.getMessage());
									} catch (CpfNaoInformadoException cpfNaoInformadoException) {
										System.out.println(cpfNaoInformadoException.getMessage());
									}

									Date dt1 = null;
									Date dt2 = null;
									String dataEntrada = null;
									String dataSaida = null;
									boolean dataVerificar = false;
									while (dataVerificar == false) {
										System.out.println("Insira a data de entrada desejada: ");
										dataEntrada = data.data();
										System.out.println("Insira a data de saída desejada: ");
										dataSaida = data.data();
										SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
										try {
											dt1 = df.parse(dataEntrada);
										} catch (ParseException e1) {
											System.out.println("Erro.");
											menu(sc, fachada, data);
										}
										try {
											dt2 = df.parse(dataSaida);
										} catch (ParseException e1) {
											System.out.println("Erro.");
											menu(sc, fachada, data);
										}

										if (dt1.after(dt2)) {
											System.out.println("Datas inválidas. Tente novamente.");
										}

										if (!dt1.after(dt2)) {
											dataVerificar = true;
										}
									}
									int contador = 0;
									Quarto quartoDisponivel = null;
									Quarto quartoIndisponivel = null;
									int contador2 = 0;
									boolean encontrou = false;

									for (Hotel hotel : fachada.getHoteis()) {
										if (hotel.getNome().equalsIgnoreCase(nome)) {
											for (Quarto q : hotel.getRepQuartos().getRepositorioQuarto()) {
												try {
													if (q.verificarDisponibilidade(dataEntrada, dataSaida) == true) {
														contador += 1;
														quartoDisponivel = q;
														encontrou = true;
													} else {
														contador2 += 1;
														quartoIndisponivel = q;
													}
												} catch (ParseException e) {
													System.out.println("Erro.");
													menu(sc, fachada, data);
												}

											}
										}
									}

									if (encontrou == true) {
										System.out.println("Hotel: " + h.getNome());
										System.out.println(contador + " quarto(s) disponivel(eis)!");
										System.out.println(contador2 + " quarto(s) ocupado(s)! ");
										System.out.println("Deseja fazer uma reserva? ");
										sc = new Scanner(System.in);
										String resposta2 = sc.nextLine();
										if (resposta2.equalsIgnoreCase("sim")) {
											try {
												quartoDisponivel.tornarQuartoOcupado(dataEntrada, dataSaida);
												Reserva reserva = new Reserva(h, quartoDisponivel, c);
												System.out.println("Valor total da reserva: R$ "
														+ reserva.getValorTotalDaReserva());
												fachada.reservarQuarto(h, c);
												menu(sc, fachada, data);
											} catch (ParseException e) {
												System.out.println("Não há quartos disponíveis nesta data.");
												procurar(sc, fachada, data);
											}

										} else {
											procurar(sc, fachada, data);
										}
									} else {
										System.out.println("Não há quartos disponíveis.");
										procurar(sc, fachada, data);
									}
									result = true;
									procurar(sc, fachada, data);
								} else {
									System.out.println("Cpf não encontrado.");
									menu(sc, fachada, data);
								}
							}
						} catch (HotelNaoCadastradoException hnce) {
							System.out.println(hnce.getMessage());
							menu(sc, fachada, data);
						}
					} else {
						System.out.println("Hotel não encontrado.");
						menu(sc, fachada, data);
					}
				} else {
					menu(sc, fachada, data);
				}
			} catch (LocalNaoEncontradoException localNaoEncontradoException) {
				System.out.println(localNaoEncontradoException.getMessage());
				menu(sc, fachada, data);
			}
		} else if (resposta == 3) {
			menu(sc, fachada, data);
		} else if (resposta == 4) {
			menuInicial(sc, fachada, data);
		} else {
			System.out.println("Ops! Parece que você inseriu uma opção inválida. Tente novamente!");
			System.out.println();
			menu(sc, fachada, data);
		}
	}

	public void avaliar(Scanner sc, Fachada fachada, DataMenu data) {
		System.out.println();
		String avaliar;
		int nota = 0;
		boolean notaValida = false;
		int nota2 = 0;
		boolean notaValida2 = false;

		System.out.println("Insira o local:");
		sc = new Scanner(System.in);
		String localHotel = sc.nextLine();

		System.out.println("Insira o nome do Hotel:");
		sc = new Scanner(System.in);
		String nomeHotel = sc.nextLine();

		if (fachada.existeLocal(localHotel)) {
			if (fachada.existeNome(nomeHotel)) {
				Hotel h = null;

				try {
					h = fachada.procurarHotel(localHotel, nomeHotel);
					System.out.println("Insira seu cpf: ");
					sc = new Scanner(System.in);
					String cpf = sc.nextLine();
					if (fachada.existeCpf(cpf)) {
						Date dt1 = null;
						Date dt2 = null;
						String dataEntrada = null;
						String dataSaida = null;
						boolean dataVerificar = false;
						while (dataVerificar == false) {
							System.out.println("Insira a data de entrada desejada: ");
							dataEntrada = data.data();
							System.out.println("Insira a data de saída desejada: ");
							dataSaida = data.data();
							SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							try {
								dt1 = df.parse(dataEntrada);
							} catch (ParseException e1) {
								System.out.println("Erro.");
								menu(sc, fachada, data);
							}
							try {
								dt2 = df.parse(dataSaida);
							} catch (ParseException e1) {
								System.out.println("Erro.");
								menu(sc, fachada, data);
							}

							if (dt1.after(dt2)) {
								System.out.println("Datas inválidas. Tente novamente.");
							}

							if (!dt1.after(dt2)) {
								dataVerificar = true;
							}
						}
						Quarto quartoIndisponivel = null;
						boolean encontrou = false;

						for (Quarto q : h.getRepQuartos().getQuartos()) {

							try {
								if (q.verificarDisponibilidade(dataEntrada, dataSaida) == false) {
									quartoIndisponivel = q;
									encontrou = true;
								}
							} catch (ParseException e) {
								menu(sc, fachada, data);
							}

						}

						if (encontrou == true) {
							try {
								quartoIndisponivel.tornarQuartoDisponivel(dataEntrada, dataSaida);
								System.out.println("Check-out realizado com sucesso!");
								h.checkOut(1);
								System.out.println(
										"O Decolar.com gostaria de saber sua experiência! \nDeseja avaliar o hotel "
												+ nomeHotel + "?");
								sc = new Scanner(System.in);
								avaliar = sc.nextLine();
								if (avaliar.equalsIgnoreCase("sim")) {
									while (!notaValida2) {
										System.out
												.println("Avalie o hotel " + h.getNome() + " com uma nota de 0 a 10: ");
										try {
											nota2 = Integer.parseInt(sc.next());
											notaValida2 = true;
											if (nota2 == 10) {
												h.setQtdNotasDez(1);
											}
										} catch (Exception e) {
											System.out.println(
													"Ops! Parece que você não inseriu uma nota válida. Tente novamente.");
											menu(sc, fachada, data);
										}

										while (nota2 > 10) {
											if (nota2 > 10) {
												System.out.println(
														"Ops! Houve algo de errado. Avalie com uma nota de 0 a 10:");
												nota2 = sc.nextInt();
											}
										}

									}
									fachada.avaliarHotel(h, nota2);
								} else {
									menu(sc, fachada, data);
								}
								System.out.println("Deseja avaliar voo?");
								sc = new Scanner(System.in);
								String avaliar2 = sc.nextLine();
								if (avaliar2.equalsIgnoreCase("sim")) {
									System.out.println("Insira o número do voo que você deseja avaliar: ");
									sc = new Scanner(System.in);
									String numeroVoo = sc.nextLine();
									if (fachada.existeNumeroDoVoo(numeroVoo) == true) {
										Voo v = null;
										try {
											v = fachada.procurarVoo(numeroVoo);
											while (!notaValida) {
												System.out.println("Avalie o voo " + v.getNumeroDoVoo()
														+ " com uma nota de 0 a 10: ");
												try {
													nota = Integer.parseInt(sc.next());
													notaValida = true;
												} catch (Exception e) {
													System.out.println(
															"Ops! Parece que você não inseriu uma nota válida. Tente novamente.");
												}

												while (nota > 10) {
													if (nota > 10) {
														System.out.println(
																"Ops! Houve algo de errado. Avalie com uma nota de 0 a 10:");
														nota = sc.nextInt();
													}
												}

											}
											fachada.avaliarVoo(v, nota);
											menu(sc, fachada, data);
										} catch (VooNaoCadastradoException e) {
											System.out.println("Voo não cadastrado.");
											menu(sc, fachada, data);
										} catch (NumeroDoVooNaoInformadoException e) {
											System.out.println("Número do voo não informado.");
											menu(sc, fachada, data);
										}
									} else {
										System.out.println("Este número de voo não existe.");
										menu(sc, fachada, data);
									}
								}
							} catch (ParseException e) {
								System.out.println("Check-out falhou.");
								menu(sc, fachada, data);
							}
						} else {
							System.out.println("Não foi encontrada nenhuma reserva no período informado.");
							menu(sc, fachada, data);
						}
					} else {
						System.out.println("Cliente não encontrado.");
						menu(sc, fachada, data);
					}
				} catch (HotelNaoCadastradoException e) {
					System.out.println("Hotel não cadastrado.");
					menu(sc, fachada, data);
				} catch (LocalNaoInformadoException e) {
					System.out.println("Local não informado.");
					menu(sc, fachada, data);
				} catch (NomeHotelNaoInformadoException e) {
					System.out.println("Nome do hotel não informado.");
					menu(sc, fachada, data);
				}
				System.out.println();

			} else {
				System.out.println("Não há hotéis no local procurado com esse nome.");
				menu(sc, fachada, data);
			}
		} else {
			System.out.println("Não há hotéis neste local.");
			menu(sc, fachada, data);
		}
	}

	public void relatorios(Scanner sc, Fachada fachada, DataMenu data) {
		System.out.println();
		System.out.println("1) Ver todos os hotéis");
		System.out.println("2) Ver todos os voos");
		System.out.println("3) Ver quartos que estão ocupados");
		System.out.println("4) Ver hotéis lotados");
		System.out.println("5) Ver voos lotados");
		System.out.println("6) Ver a lista de passageiros de um voo");
		System.out.println("7) Ver a lista de hospedes de um hotel");
		System.out.println("8) Ver cliente que mais faz reservas em hotéis");
		System.out.println("9) Ver cliente que mais compra passagens");
		System.out.println("10) Ver hotel mais procurado");
		System.out.println("11) Ver voo mais procurado");
		System.out.println("12) Ver destino de voo mais procurado");
		System.out.println("13) Ver local de hotel mais procurado");
		System.out.println("14) Ver hotel com melhor avaliação");
		System.out.println("15) Ver hotel com pior avaliação");
		System.out.println("16) Ver voo com melhor avaliação");
		System.out.println("17) Ver voo com pior avaliação");
		System.out.println("18) Voltar ao menu anterior");
		System.out.println("19) Voltar ao menu principal");
		System.out.println("20) Sair");
		int opcao = 0;
		try {
			opcao = Integer.parseInt(sc.next());
		} catch (Exception e) {

		}
		boolean sair = false;

		if (opcao == 1) {
			try {
				fachada.verHoteis();
			} catch (NaoHaHoteisCadastradosException naoHaHoteisCadastrados) {
				System.out.println(naoHaHoteisCadastrados.getMessage());
				menu(sc, fachada, data);
			}
			relatorios(sc, fachada, data);
		} else if (opcao == 2) {
			try {
				fachada.verVoos();
			} catch (NaoHaVoosCadastradosException naoHaVoosCadastradosException) {
				System.out.println(naoHaVoosCadastradosException.getMessage());
			}
			relatorios(sc, fachada, data);
		} else if (opcao == 3) {
			System.out.println("Insira a data desejada: ");
			String dataDesejada = data.data();

			int cont3 = 0;
			int cont4 = 0;

			for (Hotel h : fachada.getHoteis()) { // repHotel é um objeto do tipo RepositorioHotel
				for (Quarto q : h.getRepQuartos().getRepositorioQuarto()) {
					try {
						if (q.verificarDisponibilidade(dataDesejada, dataDesejada) == false) {
							cont3 += 1;
							cont4 += 1;
							System.out.println("Hotel: " + h.getNome() + " possui " + cont3
									+ " quarto(s) ocupado(s) no dia : " + dataDesejada);

						}
					} catch (ParseException e) {
						System.out.println("Erro.");
						relatorios(sc, fachada, data);
					}
				}

				cont3 = 0;
			}

			System.out
					.println("Na data " + dataDesejada + " existe um total geral de  " + cont4 + " quartos ocupados.");
			menu(sc, fachada, data);
		} else if (opcao == 4) {
			System.out.println("Insira a data desejada: ");
			String dataDesejada = data.data();
			int cont = 0;
			int cont2 = 0;

			for (Hotel h : fachada.getHoteis()) { // repHotel é um objeto do tipo RepositorioHotel
				for (Quarto q : h.getRepQuartos().getRepositorioQuarto()) {
					try {
						if (q.verificarDisponibilidade(dataDesejada, dataDesejada) == false) {
							cont += 1;
						}
					} catch (ParseException e) {
						System.out.println("Erro.");
						relatorios(sc, fachada, data);
					}
				}
				if (cont == h.getQuantidadeQuartos()) {
					cont2 += 1;
					System.out.println("");
					System.out.println(h.getNome());

					cont = 0;
				}

			}

			System.out.println("Quantidade de hoteis lotados no dia " + dataDesejada + ": " + cont2);
			menu(sc, fachada, data);
		} else if (opcao == 5) {
			boolean lotado = false;

			for (Voo v : fachada.getVoos()) {
				if (v.getQtdPassagensDisponiveis() == 0) {
					System.out.println("Voo " + v.getNumeroDoVoo() + " lotado.");
					lotado = true;
				}
			}

			if (lotado == false) {
				System.out.println("Não há voos lotados.");
				relatorios(sc, fachada, data);
			}

			relatorios(sc, fachada, data);
		} else if (opcao == 6) {
			System.out.println("Insira o número do voo que você deseja procurar: ");
			sc = new Scanner(System.in);
			String numeroDoVoo = sc.nextLine();
			Voo v = null;

			if (fachada.existeNumeroDoVoo(numeroDoVoo)) {
				try {
					v = fachada.procurarVoo(numeroDoVoo);
					System.out.println();
					v.verPassageiros();
					relatorios(sc, fachada, data);
				} catch (VooNaoCadastradoException vooNaoCadastradoException) {
					System.out.println(vooNaoCadastradoException.getMessage());
					relatorios(sc, fachada, data);
				} catch (NumeroDoVooNaoInformadoException numeroDoVooNaoInformadoException) {
					System.out.println(numeroDoVooNaoInformadoException.getMessage());
					relatorios(sc, fachada, data);
				}
			} else {
				System.out.println("Voo não encontrado.");
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 7) {
			System.out.println("Insira o nome do hotel que você deseja procurar: ");
			sc = new Scanner(System.in);
			String nome = sc.nextLine();
			Hotel h = null;

			if (fachada.existeNome(nome)) {

				try {
					h = fachada.procurarHotelPorNome(nome);
					System.out.println();
					h.verHospedes();
					relatorios(sc, fachada, data);
				} catch (HotelNaoCadastradoException hotelNaoCadastradoException) {
					System.out.println(hotelNaoCadastradoException.getMessage());
					relatorios(sc, fachada, data);
				}

			} else {
				System.out.println("Hotel não encontrado.");
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 8) {
			try {
				fachada.clienteQueMaisReserva();
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
			relatorios(sc, fachada, data);
		} else if (opcao == 9) {
			try {
				fachada.clienteQueMaisVoa();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 10) {
			try {
				fachada.verHotelMaisProcurado();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
			relatorios(sc, fachada, data);
		} else if (opcao == 11) {
			try {
				fachada.verVooMaisProcurado();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
			relatorios(sc, fachada, data);
		} else if (opcao == 12) {
			try {
				fachada.verDestinoMaisProcurado();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 13) {
			try {
				fachada.verLocalMaisProcurado();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 14) {
			try {
				fachada.verHotelComMaiorAvaliacao();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 15) {
			try {
				fachada.verHotelComMenorAvaliacao();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 16) {
			try {
				fachada.verVooComMaiorAvaliacao();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}
		} else if (opcao == 17) {
			try {
				fachada.verVooComMenorAvaliacao();
				relatorios(sc, fachada, data);
			} catch (ResultadoNaoEncontradoException resultadoNaoEncontradoException) {
				System.out.println(resultadoNaoEncontradoException.getMessage());
				relatorios(sc, fachada, data);
			}

		} else if (opcao == 18) {
			menu(sc, fachada, data);
		} else if (opcao == 19) {
			menuInicial(sc, fachada, data);
		} else if (opcao == 20) {
			System.out.println("Obrigada pela visita! Até a próxima... =)");
			System.exit(0);
		} else {
			System.out.println("Ops! Parece que você inseriu uma opção inválida. Tente novamente!");
			relatorios(sc, fachada, data);
		}
	}
}
