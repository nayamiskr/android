import 'package:flutter/material.dart';
import 'package:new_project/app.dart';
import 'package:new_project/info.dart';

class New extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
      appBar: AppBar(
        title: Text('酷斃了'),
        actions: [
          IconButton(
              onPressed: () => Navigator.pushReplacement(
                  context, MaterialPageRoute(builder: (context) => App())),
              icon: const Icon(Icons.home_rounded))
        ],
      ),
      body: HomePage(),
    ));
  }
}

class HomePage extends StatelessWidget {
  List<inform> informs = [
    const inform(
        ndate: '6/19', nname: '我家的玉米蛋餅會康家舞', moreinf: '我敢打賭你會在看完我的蛋餅後去洗手間'),
    const inform(
        ndate: '6/19', nname: '我家的火腿蛋餅會芭雷舞', moreinf: '我敢打賭你會在看完我的視頻後去洗手間'),
    const inform(
        ndate: '6/19', nname: '我家的雞腿蛋餅是舞舞舞', moreinf: '我敢打賭你會在看完我的視頻後去洗手間'),
    const inform(
        ndate: '6/19', nname: '我家的奶茶蛋餅會三四舞', moreinf: '我敢打賭你會在看完我的視頻後去洗手間'),
    const inform(
        ndate: '6/19', nname: '不是我家的甲魚蛋餅是舞神', moreinf: '我敢打賭你會在看完我的視頻後去洗手間'),
    const inform(
        ndate: '6/19', nname: '我家的蛋餅其實不會康家舞', moreinf: '我敢打賭你會在看完我的視頻後去洗手間'),
    const inform(
        ndate: '6/19', nname: '我家的蛋餅會康家舞', moreinf: '我敢打賭你會在看完我的視頻後去洗手間'),
  ];
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: informs.length,
        itemBuilder: (BuildContext context, int index) {
          final inform = informs[index];
          return Card(
            color: Colors.amber,
            child: ListTile(
              contentPadding: const EdgeInsets.all(20.0),
              title: Text(inform.nname, style: const TextStyle(fontSize: 25)),
              subtitle: Text(
                inform.ndate + "發布的消息",
                style: TextStyle(fontSize: 20),
              ),
              onTap: () => Navigator.pushReplacement(context,
                  MaterialPageRoute(builder: (context) => const infomore())),
            ),
          );
        });
  }
}

class inform {
  final String ndate;
  final String nname;
  final String moreinf;

  const inform({
    required this.ndate,
    required this.nname,
    required this.moreinf,
  });
}
