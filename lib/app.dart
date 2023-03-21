import 'package:flutter/material.dart';
// ignore: unused_import
import 'package:google_fonts/google_fonts.dart';
import 'package:new_project/list.dart';

class App extends StatelessWidget {
  const App({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text('首頁'),
          ),
          body: const Homepage()),
    );
  }
}

class Homepage extends StatelessWidget {
  const Homepage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          Card(
            elevation: 20,
            color: Color.fromARGB(255, 218, 45, 160),
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: <Widget>[
                  const Text(
                    "最新消息",
                    style:
                        TextStyle(fontSize: 30, fontWeight: FontWeight.normal),
                  ),
                  TextButton(
                    onPressed: () => Navigator.pushReplacement(context,
                        MaterialPageRoute(builder: (context) => New())),
                    child: const Text(
                      "點我一下讓你看更多",
                      style: TextStyle(fontSize: 20),
                      selectionColor: Color.fromARGB(255, 238, 235, 235),
                    ),
                  ),
                ],
              ),
            ),
          ),
          Card(
            elevation: 20,
            color: Color.fromARGB(255, 32, 45, 226),
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: <Widget>[
                  const Text(
                    "競賽資訊",
                    style:
                        TextStyle(fontSize: 30, fontWeight: FontWeight.normal),
                  ),
                  TextButton(
                    onPressed: () => Navigator.pushReplacement(context,
                        MaterialPageRoute(builder: (context) => Tab())),
                    child: const Text(
                      "點我一下讓你看更多",
                      style: TextStyle(fontSize: 20),
                      selectionColor: Color.fromARGB(255, 238, 235, 235),
                    ),
                  ),
                ],
              ),
            ),
          ),
          Card(
            elevation: 20,
            color: const Color.fromARGB(255, 218, 85, 45),
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: <Widget>[
                  const Text(
                    "職種介紹",
                    style:
                        TextStyle(fontSize: 30, fontWeight: FontWeight.normal),
                  ),
                  TextButton(
                    onPressed: () => Navigator.pushReplacement(context,
                        MaterialPageRoute(builder: (context) => Tab())),
                    child: const Text(
                      "點我一下讓你看更多",
                      style: TextStyle(fontSize: 20),
                      selectionColor: Color.fromARGB(255, 238, 235, 235),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    ));
  }
}
