using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace COMPILATIONEXO1
{
    internal class Controller
    {
        int [,] read()
        {
            int[,] T = new int[3, 3];
            try
            {
                using (StreamReader str = new StreamReader(@"C:\Users\pc\source\repos\COMPILATIONEXO1\TextFile1.txt"))
                {
                    int l = int.Parse(str.ReadLine());
                    int c = int.Parse(str.ReadLine());
                    for(int i=0;i<l;i++)
                        for(int j=0;j<c;j++)
                            T[i,j]= int.Parse(str.ReadLine());
                }
            }
            catch (Exception e) { }

                return T;
        }
          int[] convert(string mot)
        {
            int[] mt = new int[mot.Length];
            for (int i = 0; i < mot.Length; i++)
            {
                if (mot[i] == 'a')
                    mt[i] = 0;
                if (mot[i] == 'b')
                    mt[i] = 1;
                if (mot[i] == 'c')
                    mt[i] = 2;
            }

                return mt;

        }
        int trace( string mot)
        {
            
            int[,] T = this.read();
            int[] mt = this.convert(mot);
            int cnt = 1;
            int tr = T[0, mt[0]];
            Console.WriteLine(tr);
            while (cnt<mot.Length && tr!=-1)
            {
                
                tr = T[tr, mt[cnt]];
                cnt++;
            }
            return tr;
        }

        public bool reconaissance(string mot)
        {
            int tr=this.trace(mot);
            if (tr == 0 || tr == 1)
                return true;
            else
                return false;

        }

    }
}
