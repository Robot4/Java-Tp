namespace COMPILATIONEXO1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Class1 c = new Class1();
            if (this.textBox1.Text.Length != 0)
            {
                if (c.validation(this.textBox1.Text))
                {
                    if (c.reconaissance(this.textBox1.Text))
                        this.result.Text = "Le mot " + this.textBox1.Text + " est accept� ";
                    else
                        this.result.Text = "Le mot " + this.textBox1.Text + " n'est pas accept� ";

                }
                else
                    this.result.Text = "Le mot " + this.textBox1.Text + " comporte des �l�ments diff�rents de a , b et c ";
            }
            else
                this.result.Text = "Le mot vide est accept� ! ";

        }
    }
}