#########################################################################################
#                                                                                       #
#                                    Daniel Cardoso                                     #
#                               daniel.cardoso18@live.com                               #
#                                                                                       #
#                                       DiCorder                                        #
#                                                                                       #
#                         Last Updated: 2011                                            #
#                                   by: Daniel Cardoso                                  #
#                                                                                       #
#########################################################################################



Este projecto � uma aplica��o para Android.
Sendo assim para correr a aplica��o compilando o c�digo a maneira mais f�cil � atrav�s do Eclipse.
Instru��es para correr o projecto:

1-System requirements:
http://developer.android.com/sdk/requirements.html

2-Sacar a sdk do android
http://developer.android.com/sdk/index.html

3-Instalar o ADT Plugin no Eclipse
http://developer.android.com/sdk/eclipse-adt.html#installing

4-Adicionar plataformas Android e outros componentes
http://developer.android.com/sdk/installing.html (Step 4)
NOTA: para a SDK platform (que ter� de escolher pelo menos 1) escolha a 2.3.3 ou seja API 10

5-Criar um AVD (Android Virtual Device)
http://developer.android.com/resources/tutorials/hello-world.html#avd (Interessa s� a parte "Create an AVD")
NOTA: escolha o target 2.3.3. na skin escolha o radio button "built-in" e no spinner escolha "QVGA" para escolher o mesmo tipo de ecr� usado por n�s no desenvolvimento.

6-Importar projecto
File->Import->General->Existing Projects into Workspace
depois escolha Select root directory e seleccione a pasta raiz do projecto (DiCorder)
Finish

7-Ap�s o Eclipse ter feito o build do projecto (pode demorar alguns segundos), corra o projecto
Run->Run Configurations->Android Application
Na tab Android (a que aparece selecionada inicialmente) fa�a Browse para o projecto
Na tab Target meta Automatic e escolha o AVD criado anteriormente
Fa�a Run
Pode demorar um bocado a carregar pois tem de carregar o emulador, instalar o projecto no emulador e depois inicia automaticamente a aplica��o.
Na Console do Eclipse aparecem mensagens a indicar o estado do lancamento.
Ao fazer Run pode aparecer uma janela a pedir o video source para usar a camera do laptop/desktop para emular a camera do android.
Quando na console aparecer a mensagem: "ActivityManager: Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=ist.leic.ipm.dicorder/.MenuActivity }" � porque j� iniciou a aplica��o.
Em pr�ncipio ter� de desbloquear o android para ele entrar na aplica��o pois o emulador por omiss�o come�a com o teclado bloqueado. Use o rato como se fosse o seu dedo no touch screen.

Obrigado pelo trabalho.
Cumprimentos
Daniel Cardoso n� 66964
Francisco Raposo n� 66986
Miguel Arag�o n� 67043