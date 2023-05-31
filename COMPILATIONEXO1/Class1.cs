using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace COMPILATIONEXO1
{
    internal class Class1
    {
        int[,] read()
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

        int [] convert(string chaine)
        {
            int[] T = new int[chaine.Length];
            for(int i=0;i<chaine.Length;i++)
            {
                if (chaine[i] == 'a')
                    T[i] = 0;
                if(chaine[i] == 'b')
                    T[i] = 1;
                if(chaine[i] == 'c')
                    T[i] = 2;
            }
            return T;
        }

        int trace(string chaine)
        {
            int[] mot = this.convert(chaine);
            int[,] matrice = this.read();
            int tr = matrice[0, mot[0]];
            int cnt = 1;
            while(cnt<chaine.Length && tr!=-1)
            {
                tr=matrice[tr, mot[cnt]];
                cnt++;
            }
            return tr;
        }
        public bool validation(string chaine)
        {
            for (int i = 0; i < chaine.Length; i++)
            {
                if (chaine[i] != 'a' && chaine[i] != 'b' && chaine[i] != 'c')
                    { return false; }
            }
                    return true;
        }

        public bool reconaissance(string chaine)
        {
            if (this.trace(chaine) == 0 || this.trace(chaine) == 1)
                return true;
            else
                return false;
        }
    }
}
