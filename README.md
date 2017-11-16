# KittenMaxit - now using LibGDX

LibGDX kütüphanesi yardımıyla yapılmış Maxit oyunu. Eğer geliştirilme sürecinde destek olmak istiyorsanız, proje eclipse'e gömülmeye hazır durumdadır. Eğer eclipse'inize gömme konusunda yardıma ihtiyaç duyuyorsanız, LibGDX'in konu hakkındaki şu yazısını okuyun : https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline#packaging-for-the-desktop.

Distributions klasöründen istediğiniz versiyona tıklayıp, ardından raw (çiğ) formatta göster demenizi takiben dosya inmeye başlayacaktır. İndirme konusunda sıkıntı yaşayanlar için indirme linki :

https://github.com/ahmetkasif/KittenMaxit/blob/master/distributions/kmaxit+%20v1.6.1.jar?raw=true

If you want to contribute, project is ready to be imported to eclipse.

Conributors

[Abdullah Öğük](https://github.com/abdullahoguk)


**Problem**     
 Writing a program to play MAXIT. The board is represented as an N-by-N grid of numbers randomly placed at the start of the game. One position is designated as the initial current 
position. Two players alternate turns. At each turn, a player must select a grid element in the 
current row or column. The value of the selected position is added to the player’s score, and 
that position becomes the current position and cannot be selected again. Players alternate 
until all grid elements in the current row and column are already selected, at which point the 
game ends and the player with the higher score wins.


**Solution**   
Solution solution is based on greedy approach. “A greedy algorithm is an algorithm that follows the problem solving heuristic of making the locally optimal choice at each stage with the hope of finding a global optimum. In many problems, a greedy strategy does not in general produce an optimal solution, but nonetheless a greedy heuristic may yield locally optimal solutions that approximate a global optimal solution in a reasonable time.”   


**Algoritm**
```Java
for all i values starting from 0 to point of last chosen point on x axis, 
	if the previous number is less than current number, 
		assign current number 

for all i values starting from the last chosen point to 5 on x axis, 
	if the previous number is less than current number, 
		assign current number 

for all k values starting from 0 to point of last chosen point on y axis, 
	if the previous number is less than current number, 
		assign current number 

for all k values starting from the last chosen point to 5 on y axis, 
	if the previous number is less than current number, 
		assign current number
```

#Screenshots
![Maxit1](http://i.imgur.com/3QvTDrN.png)
![Maxit2](http://i.imgur.com/G1rezQr.png)



 
